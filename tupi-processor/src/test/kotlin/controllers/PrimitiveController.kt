package controllers

import tupi.annotations.*
import tupi.annotations.enumerators.OperationType

@SwaggerRoute("/api/primitive", "Primitive controller with one operation and it returns a primitive type")
class PrimitiveController {

    @SwaggerOperation(OperationType.GET, "", "Operation that return key of primitive type")
    @SwaggerResponses([SwaggerResponse(200, "Success", Int::class)])
    fun getKey() {
    }
}

