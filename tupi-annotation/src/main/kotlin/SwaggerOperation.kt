package tupi.annotations

import tupi.annotations.enumerators.*

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SwaggerOperation(val method: OperationType, val operationPath: String, val summary: String = "")