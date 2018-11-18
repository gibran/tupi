package controllers

import tupi.annotations.SwaggerOperation
import tupi.annotations.SwaggerResponse
import tupi.annotations.SwaggerResponses
import tupi.annotations.SwaggerRoute
import tupi.annotations.enumerators.OperationType
import tupi.processor.test.dtos.simpleClass

@SwaggerRoute("/api/simple", "Simple controller with one operation and it returns a simple class")
class SimpleController {
    @SwaggerOperation(OperationType.GET, "", "Operation that return key of simple class")
    @SwaggerResponses([SwaggerResponse(200, "Success", simpleClass::class)])
    fun getData() {
    }
}


