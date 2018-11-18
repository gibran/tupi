package tupi.processor.yaml

import com.sun.tools.javac.code.Type
import tupi.annotations.*
import tupi.processor.extensions.*

import javax.lang.model.element.*
import javax.lang.model.type.*

internal class YamlSerializer {

    private val operations = ArrayList<YamlOperation>()
    private val definitions = HashMap<String, YamlDefinition>()
    private val yamlDefinitions = HashMap<String, String>()

    fun addRoute(controllerElement: Element) {

        val annotationController = controllerElement.getAnnotation(SwaggerRoute::class.java) ?: return

        controllerElement.enclosedElements
                .filter { it.getAnnotation(SwaggerOperation::class.java) != null }
                .forEach { operationElement ->

                    if (operationElement.kind == ElementKind.METHOD && operationElement.modifiers.contains(Modifier.PUBLIC)) {

                        val operationAnnotation = operationElement.getAnnotation(SwaggerOperation::class.java)
                        val responsesAnnotation = operationElement.getAnnotation(SwaggerResponses::class.java)
                        val parametersAnnotation = operationElement.getAnnotation(SwaggerParameters::class.java)

                        val yamlOperation = YamlOperation(annotationController.routePath, operationElement.simpleName.toString(), operationAnnotation)

                        buildResponses(responsesAnnotation, yamlOperation)

                        buildParameters(parametersAnnotation, yamlOperation)

                        operations.add(yamlOperation)
                    }
                }
    }

    private fun addDefinition(typeMirror: TypeMirror, typeElement: Element) {
        if (typeMirror.toString() != "kotlin.Unit" && !typeMirror.isKotlinType()) {
            val yamlType = YamlDefinition(typeElement as TypeElement)
            definitions[typeMirror.toString()] = yamlType
        }
    }

    private fun getReturnedType(responseAnnotation: SwaggerResponse): TypeMirror? {
        return try {
            responseAnnotation.type
            null
        } catch (e: MirroredTypeException) {
            e.typeMirror
        }
    }

    private fun buildDefinitions(yamlDefinitions: HashMap<String, String>) {
        definitions.forEach { definition ->
            definition.value.toYaml(yamlDefinitions)
        }
    }

    private fun buildParameters(parametersAnnotation: SwaggerParameters?, yamlOperation: YamlOperation) {
        parametersAnnotation?.values?.forEach { parameter ->
            val paramField = YamlParameter(parameter)
            yamlOperation.parameters[parameter.paramName] = paramField

            val returnedType = (paramField.parameterType as Type).asElement() as TypeElement

            addDefinition(paramField.parameterType, returnedType)
        }
    }

    private fun buildResponses(responsesAnnotation: SwaggerResponses?, yamlOperation: YamlOperation) {
        responsesAnnotation?.values?.forEach { responseAnnotation ->

            val typeMirror = getReturnedType(responseAnnotation) ?: return

            val returnedType = (typeMirror as Type).asElement()

            yamlOperation.responses[typeMirror.toString()] = YamlResponse(returnedType, responseAnnotation)
            addDefinition(typeMirror, returnedType)
        }
    }

    fun write(): String {
        buildDefinitions(yamlDefinitions)

        val result = StringBuilder()

        result.appendln("openapi: 3.0.0")
                .appendln("info:")
                .appendln("\tversion: 1.0.0")
                .appendln("\ttitle: CARD") //TODO: Obter esta informação da configuração

        result.appendln("paths:")
        operations.forEach { routes ->
            result.appendln(routes.toString(lineIdent = "\t"))
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

}