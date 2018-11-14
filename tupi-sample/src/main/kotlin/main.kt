package tupi.sample

import tupi.annotations.*
import tupi.annotations.enumerators.OperationType
import tupi.annotations.enumerators.ParameterType

fun main (args : Array<String>) {
    println("Gerando...")
}

@SwaggerRoute("/accounts")
class Teste {

    @SwaggerOperation(OperationType.GET, "/{id}", "Show data for a given account.")
    @SwaggerResponses([SwaggerResponse(200, "Successful operation", Order::class)])
                      // SwaggerResponse(400, "NotFound")])
    fun Obter(@SwaggerParameter(ParameterType.PATH) id: String) : String{
        return "ok"
    }

    @SwaggerOperation(OperationType.GET, "", "O objetivo desta rota é retornar uma lista de ordens")
    @SwaggerResponses([SwaggerResponse(400, "ERROS", String::class)])
    fun Listar() : String {
        return "ok"
    }
}


