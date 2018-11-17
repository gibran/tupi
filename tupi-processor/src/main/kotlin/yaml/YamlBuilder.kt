package tupi.processor.yaml

import tupi.annotations.*
import tupi.processor.extensions.*

import javax.lang.model.element.*
import javax.lang.model.type.*
import javax.lang.model.util.Types

internal class YamlBuilder(private val types: Types) {

    private val operations = ArrayList<YamlOperation>()
    private val definitions = HashMap<String, YamlDefinition>()
    private val yamlDefinitions = HashMap<String, String>()

    fun addRoute(controllerElement: Element) {

        val annotationController = controllerElement.getAnnotation(SwaggerRoute::class.java)

        controllerElement.enclosedElements
                .filter { it.getAnnotation(SwaggerOperation::class.java) != null }
                .forEach { operationElement ->

                    if (operationElement.kind == ElementKind.METHOD && operationElement.modifiers.contains(Modifier.PUBLIC)) {

                        val operationAnnotation = operationElement.getAnnotation(SwaggerOperation::class.java)
                        val responsesAnnotation = operationElement.getAnnotation(SwaggerResponses::class.java)
                        val parametersAnnotation = operationElement.getAnnotation(SwaggerParameters::class.java)
                        //TODO: Obter parametros

                        val yamlOperation = YamlOperation(annotationController.routePath, operationElement.simpleName.toString(), operationAnnotation)

                        responsesAnnotation?.values?.forEach { responseAnnotation ->

                            val typeMirror = getReturnedType(responseAnnotation) ?: return
                            val returnedType = types.asElement(typeMirror) as TypeElement

                            yamlOperation.responses[typeMirror.toString()] = YamlResponse(returnedType, responseAnnotation)
                            addDefinition(typeMirror, returnedType)
                        }

                        parametersAnnotation?.values?.forEach { parameter ->
                            val paramField = YamlParameter(parameter)
                            yamlOperation.parameters[parameter.paramName] = paramField

                            val returnedType = types.asElement(paramField.parameterType) as TypeElement
                            addDefinition(paramField.parameterType, returnedType)
                        }

                        operations.add(yamlOperation)
                    }
                }
    }

    private fun addDefinition(typeMirror: TypeMirror, typeElement: TypeElement) {

        val yamlType = YamlDefinition(typeElement)

        if (typeMirror.toString() != "kotlin.Unit" && !typeMirror.isKotlinType())
            definitions[typeMirror.toString()] = yamlType
    }

    private fun getReturnedType(responseAnnotation: SwaggerResponse): TypeMirror? {
        return try {
            responseAnnotation.type
            null
        } catch (e: MirroredTypeException) {
            e.typeMirror
        }
    }

    fun write(): String {
        buildDefinitions(yamlDefinitions)

        val result = StringBuilder()

        result.appendln("openapi: 3.0.0")
                .appendln("info:")
                .appendln("\tversion: 1.0.0")
                .appendln("\ttitle: CARD")

        result.appendln("paths:")
        operations.forEach { routes ->
            result.appendln(routes.toString())
        }

        result.appendln("definitions:")
        yamlDefinitions.forEach { definition ->
            result.append(definition.value)
        }

        result.appendln("schemes:")
                .appendln("- http")
                .append("host: localhost:8080")

        return result.toString().replaceAll("\t", "  ")
    }

    private fun buildDefinitions(yamlDefinitions: HashMap<String, String>) {
        definitions.forEach { definition ->
            definition.value.toYaml(yamlDefinitions)
        }
    }
}