package tupi.processor.yaml

import tupi.annotations.SwaggerOperation
import tupi.processor.extensions.toString

class YamlOperation(private val routePath: String, private val methodName: String, val operation: SwaggerOperation) {

    val responses = HashMap<String, YamlResponse>()

    override fun toString(): String {

        val result = StringBuilder()
        val operationId = "${operation.method.toString().toLowerCase()}_${methodName.toLowerCase()}"

        result.appendln("$routePath${operation.operationPath}:")
            .appendln("\t${operation.method.toString().toLowerCase()}:")
            .appendln("\t\tsummary: '${operation.summary}'")
            .appendln("\t\toperationId: $operationId")
            //.appendln("\t\tdescription: ") //TODO: Incluir esta informaçao
            .appendln("\t\tproduces:")
            .appendln("\t\t- 'application/json'") //TODO: obter estas informações de um attribute

        //TODO: Processar parameters
        //result.appendln("\t\tparameters: ")

        if (responses.any()) {
            result.appendln("\t\tresponses: ")

            responses.forEach { response ->
                result.appendln(response.value.toString())
            }
        }

        return result.toString(lineIdent = "\t")
    }
}