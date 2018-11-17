package tupi.annotations

import tupi.annotations.enumerators.ParameterType
import kotlin.reflect.KClass

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class SwaggerParameter(val paramType: ParameterType, val paramName: String, val type: KClass<*> = Unit::class, val isRequired: Boolean, val description: String)


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class SwaggerParameters(val values: Array<SwaggerParameter>)