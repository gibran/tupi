package controllers

import tupi.annotations.SwaggerOperation
import tupi.annotations.SwaggerResponse
import tupi.annotations.SwaggerResponses
import tupi.annotations.enumerators.OperationType
import tupi.processor.test.dtos.simpleClass

class SimpleWithoutRouteAnnotationController {

    @SwaggerOperation(OperationType.GET, "", "Operation that return key of simple class")
    @SwaggerResponses([SwaggerResponse(200, "Success", simpleClass::class)])
    fun getData() {
    }
}


