package tupi.processor

import com.google.auto.service.AutoService
import tupi.annotations.*
import tupi.processor.yaml.YamlSerializer
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.*
import javax.tools.Diagnostic


@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8) // to support Java 8
@SupportedOptions(GenDocSwagger.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class GenDocSwagger : AbstractProcessor() {

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> =
            mutableSetOf(SwaggerRoute::class.java.canonicalName)

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latest()

    override fun process(
            annotations: MutableSet<out TypeElement>,
            roundEnv: RoundEnvironment
    ): Boolean {

        val annotatedElements = roundEnv.getElementsAnnotatedWith(SwaggerRoute::class.java)

        if (annotatedElements.isEmpty()) return false

        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: run {
            processingEnv.messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "Can't find the target directory for generated Kotlin files."
            )
            return false
        }

        val fileName = "swagger_api"

        val yaml = YamlSerializer()

        File(kaptKotlinGeneratedDir, "$fileName.yml").bufferedWriter().use { out ->

            annotatedElements
                    .forEach { controllerElement ->
                        if (controllerElement.kind != ElementKind.CLASS) {
                            processingEnv.messager.printMessage(
                                    Diagnostic.Kind.ERROR,
                                    "Can only be applied to class,  element: $controllerElement "
                            )
                            return false
                        }

                        yaml.addRoute(controllerElement)
                    }

            out.write(yaml.write())
        }
        return true
    }
}