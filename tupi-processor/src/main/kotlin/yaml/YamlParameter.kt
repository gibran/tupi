package tupi.processor.yaml

import tupi.annotations.SwaggerParameter
import tupi.processor.extensions.toString
import tupi.processor.yaml.extensions.toYaml
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.VariableElement

class YamlParameter (private val parameterType: VariableElement) {

    fun toString(context: ProcessingEnvironment): String {

        val result = StringBuilder()

        val parameter = parameterType.getAnnotation(SwaggerParameter::class.java)
        val isRequired =  !parameterType.annotationMirrors.any { it.annotationType.toString() == "org.jetbrains.annotations.Nullable" }

        result.appendln("- name: ${parameterType.simpleName}")
                .appendln("\tin: ${parameter.type.toString().toLowerCase()}")
                .appendln("\trequired: $isRequired")
                .appendln("\tschema:")
                .append("\t\t${parameterType.asType().toYaml(context)}") //TODO: Write type

        return result.toString(lineIdent = "\t\t\t")
    }
}