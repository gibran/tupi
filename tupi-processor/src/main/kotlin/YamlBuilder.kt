package tupi.processor

import tupi.annotations.*
import tupi.processor.extensions.replaceAll
import tupi.processor.yaml.YamlOperation
import tupi.processor.yaml.YamlParameter
import tupi.processor.yaml.YamlResponse
import tupi.processor.yaml.YamlType
import tupi.processor.yaml.extensions.isPrimitiveTypes
import javax.lang.model.element.*
import javax.lang.model.type.*
import javax.annotation.processing.ProcessingEnvironment

class YamlBuilder(private val context: ProcessingEnvironment) {

    private val operations = ArrayList<YamlOperation>()
    private val definitions = HashMap<String, YamlType>()

    fun addRoute(controllerElement: Element) {

        val annotationController = controllerElement.getAnnotation(SwaggerRoute::class.java)

        controllerElement.enclosedElements.forEach { operationElement ->

            if (operationElement.kind == ElementKind.METHOD && operationElement.modifiers.contains(Modifier.PUBLIC)) {

                val operationAnnotation = operationElement.getAnnotation(SwaggerOperation::class.java)
                val responsesAnnotation = operationElement.getAnnotation(SwaggerResponses::class.java)

                val yamlOperation = YamlOperation(annotationController.routePath, operationElement.simpleName.toString(), operationAnnotation)

                responsesAnnotation.values.forEach { responseAnnotation ->

                    val typeMirror = getReturnedType(responseAnnotation)
                    val returnedType = context.typeUtils.asElement(typeMirror) as TypeElement

                    yamlOperation.responses[typeMirror.toString()] = YamlResponse(returnedType, responseAnnotation)
                    addDefinition(returnedType)
                }

                (operationElement as ExecutableElement).parameters
                        .filter { it.getAnnotation(SwaggerParameter::class.java) != null }
                        .forEach { param ->
                            yamlOperation.parameters[param.simpleName.toString()] = YamlParameter(param)

                            //TODO: Se o parâmetro for não for uma classe primitiva e não for uma classe do kotlin, deve ser incluída em definition
                            //addDefinition()
                        }

                operations.add(yamlOperation)
            }
        }
    }

    private fun addDefinition(target: TypeElement) {

        val typeMirror = target.asType()
        val yamlType = YamlType(target, target.asType())

        if (typeMirror.toString() != "kotlin.Unit" && !target.isPrimitiveTypes())
            definitions[typeMirror.toString()] = yamlType
    }

    fun write(): String {

        val result = StringBuilder()

        result.appendln("openapi: 3.0.0")
                .appendln("info:")
                .appendln("\tversion: 1.0.0")
                .appendln("\ttitle: CARD")

        result.appendln("paths:")
        operations.forEach { routes ->
            result.appendln(routes.toString(context))
        }

        result.appendln("definitions:")
        definitions.forEach { definition ->
            result.append("${definition.value.toYaml(context)}")
        }

        result.appendln("schemes:")
                .appendln("- http")
                .append("host: localhost:8080")

        return result.toString().replaceAll("\t", "  ")
    }

    private fun getReturnedType(responseAnnotation: SwaggerResponse): TypeMirror? {
        return try {
            responseAnnotation.type

            null
        } catch (e: MirroredTypeException) {
            e.typeMirror
        }
    }

}