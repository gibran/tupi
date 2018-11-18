package controllers

import tupi.annotations.*
import tupi.annotations.enumerators.OperationType
import tupi.annotations.enumerators.ParameterType
import tupi.processor.test.dtos.simpleClass

@SwaggerRoute("/api/simple", "Simple controller with one operation and it returns a simple class")
class SimpleWithAllKindOfParameterController {
    @SwaggerOperation(OperationType.GET, "/{id}", "Operation that return key of simple class")
    @SwaggerResponses([SwaggerResponse(200, "Success", simpleClass::class)])
    @SwaggerParameters([
        SwaggerParameter(ParameterType.PATH, "id", String::class, true, "identifier"),
        SwaggerParameter(ParameterType.QUERY, "name", String::class, true, "name"),
        SwaggerParameter(ParameterType.HEADER, "version", String::class, true, "version of api")
    ])
    fun getData(id: String, name: String) {
    }
}


