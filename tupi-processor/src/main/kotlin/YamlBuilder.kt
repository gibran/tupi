package tupi.processor

import tupi.annotations.*
import tupi.processor.extensions.replaceAll
import tupi.processor.yaml.YamlOperation
import tupi.processor.yaml.YamlResponse
import tupi.processor.yaml.extensions.isPrimitiveTypes
import tupi.processor.yaml.extensions.toYaml
import javax.lang.model.element.*
import javax.lang.model.type.*
import javax.annotation.processing.ProcessingEnvironment

class YamlBuilder (private val context : ProcessingEnvironment) {

    private val operations = ArrayList<YamlOperation>()
    private val definitions = HashMap<String, TypeElement>()

    fun addRoute (controllerElement : Element) {

        val annotationController = controllerElement.getAnnotation(SwaggerRoute::class.java)

        controllerElement.enclosedElements.forEach{ operationElement ->

            if (operationElement.kind == ElementKind.METHOD && operationElement.modifiers.contains(Modifier.PUBLIC)) {

                val operationAnnotation = operationElement.getAnnotation(SwaggerOperation::class.java)
                val responsesAnnotation = operationElement.getAnnotation(SwaggerResponses::class.java)

                val yamlOperation = YamlOperation(annotationController.routePath, operationElement.simpleName.toString(), operationAnnotation)

                responsesAnnotation.values.forEach { responseAnnotation ->

                    val typeMirror = getReturnedType(responseAnnotation)
                    val returnedType = context.typeUtils.asElement(typeMirror)
                    val response = YamlResponse(returnedType as TypeElement, responseAnnotation)

                    yamlOperation.responses.put(typeMirror.toString(), response)

                    if (typeMirror.toString() != "kotlin.Unit" && !returnedType.isPrimitiveTypes()) //TODO: Remover os primitivos
                        definitions[typeMirror.toString()] = returnedType
                }

                operations.add(yamlOperation)
            }
        }
    }

    fun write(): String {

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
        definitions.forEach { definition ->
            result.appendln("${definition.value.toYaml()}")
        }

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