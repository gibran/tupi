package tupi.processor.yaml

import tupi.annotations.SwaggerParameter
import tupi.processor.extensions.*
import javax.lang.model.type.MirroredTypeException
import javax.lang.model.type.TypeMirror

internal class YamlParameter(private val parameter: SwaggerParameter) {

    var parameterType: TypeMirror = getType(parameter)!!
        private set

    override fun toString(): String {

        val result = StringBuilder()

        result.appendln("- name: ${parameter.paramName}")
                .appendln("\tin: ${parameter.paramType.toString().toLowerCase()}")
                .appendln("\tdescription: ${parameter.description}")
                .appendln("\trequired: ${parameter.isRequired}")
                .appendln("\tschema:")
                .append("\t${this.parameterType.toYaml()}")

        return result.toString(lineIdent = "\t\t\t")
    }

    private fun getType(parameterType: SwaggerParameter): TypeMirror? {
        return try {
            parameterType.type
            null
        } catch (e: MirroredTypeException) {
            e.typeMirror
        }
    }
}