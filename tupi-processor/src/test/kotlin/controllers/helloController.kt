package controllers

import tupi.annotations.*
import tupi.annotations.enumerators.OperationType
import tupi.processor.test.dtos.simpleClass

@SwaggerRoute("/api/hello", "dados hello")
class HelloController {
    @SwaggerOperation(OperationType.GET, "", "Obter dados hello")
    @SwaggerResponses([SwaggerResponse(200, "Success", simpleClass::class)])
    fun obterDados() {
    }
}