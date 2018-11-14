package tupi.annotations

import tupi.annotations.enumerators.ParameterType

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class SwaggerParameter(val type : ParameterType)