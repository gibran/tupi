package controllers

import tupi.annotations.SwaggerOperation
import tupi.annotations.SwaggerResponse
import tupi.annotations.SwaggerResponses
import tupi.annotations.SwaggerRoute
import tupi.annotations.enumerators.OperationType
import tupi.processor.test.dtos.complexClass

@SwaggerRoute("/api/simple", "Simple controller with one operation and it returns a complex class")
class SimpleWithComplexResponseController {
    @SwaggerOperation(OperationType.GET, "", "Operation that return key of complex class")
    @SwaggerResponses([SwaggerResponse(200, "Success", complexClass::class)])
    fun getData() {
    }
}