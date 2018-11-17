package tupi.annotations

import kotlin.reflect.KClass



@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SwaggerResponse(val status: Short, val description: String, val type: KClass<*> = Unit::class)

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SwaggerResponses(val values : Array<SwaggerResponse>)
