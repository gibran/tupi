package tupi.processor.yaml

import tupi.annotations.SwaggerOperation
import tupi.processor.extensions.toString

internal class YamlOperation(private val routePath: String, private val methodName: String, private val operation: SwaggerOperation) {

    val responses = HashMap<String, YamlResponse>()
    val parameters = HashMap<String, YamlParameter>()

    fun toString(lineIdent: String): String {

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
            result.appendln("\t\tparameters:")

        parameters.forEach { parameter ->
            result.appendln(parameter.value.toString(lineIdent = "$lineIdent\t\t"))
        }

        if (responses.any()) {
            result.appendln("\t\tresponses:")

            responses.forEach { response ->
                result.appendln(response.value.toString("$lineIdent\t\t"))
            }
        }

        return result.toString(lineIdent = lineIdent)
    }
}