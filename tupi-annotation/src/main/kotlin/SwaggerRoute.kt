package tupi.annotations

@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class SwaggerRoute (val routePath: String, val summary : String = "")