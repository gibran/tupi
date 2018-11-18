package controllers

import tupi.annotations.*
import tupi.annotations.enumerators.OperationType
import tupi.annotations.enumerators.ParameterType
import tupi.processor.test.dtos.simpleClass

@SwaggerRoute("/api/simple", "Simple controller with one operation and it returns a simple class")
class SimpleWithBodyParameterController {
    @SwaggerOperation(OperationType.POST, "", "Operation that return key of simple class")
    @SwaggerResponses([
        SwaggerResponse(200, "Success", simpleClass::class)
    ])
    @SwaggerParameters([
        SwaggerParameter(ParameterType.BODY, "id", simpleClass::class, true, "identifier")
    ])
    fun getData(data: simpleClass) { }
}


