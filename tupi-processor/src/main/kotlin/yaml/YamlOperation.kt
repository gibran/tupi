package tupi.processor.yaml

import tupi.annotations.SwaggerOperation
import tupi.processor.extensions.toString
import javax.annotation.processing.ProcessingEnvironment

class YamlOperation(private val routePath: String, private val methodName: String, val operation: SwaggerOperation) {

    val responses = HashMap<String, YamlResponse>()
    val parameters = HashMap<String, YamlParameter>()
    fun toString(context: ProcessingEnvironment): String {

        val result = StringBuilder()
        val operationId = "${operation.method.toString().toLowerCase()}_${methodName.toLowerCase()}"

        result.appendln("$routePath${operation.operationPath}:")
            .appendln("\t${operation.method.toString().toLowerCase()}:")
            .appendln("\t\tsummary: '${operation.summary}'")
            .appendln("\t\toperationId: $operationId")
            //.appendln("\t\tdescription: ") //TODO: Incluir esta informaçao
            .appendln("\t\tproduces:")
            .appendln("\t\t- 'application/json'") //TODO: obter estas informações de um attribute

        if (parameters.any())
            result.appendln("\t\tparameters: ")

        parameters.forEach { parameter ->
            result.appendln("${parameter.value.toString(context)}")
        }

        if (responses.any()) {
            result.appendln("\t\tresponses: ")

            responses.forEach { response ->
                result.append(response.value.toString())
            }
        }

        return result.toString(lineIdent = "\t")
    }
}