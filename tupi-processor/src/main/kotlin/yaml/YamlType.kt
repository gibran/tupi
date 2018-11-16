package tupi.processor.yaml

import tupi.processor.yaml.extensions.toYaml
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.TypeElement
import javax.lang.model.type.TypeMirror

class YamlType(private val typeElement: TypeElement, private val typeMirror: TypeMirror) {

    fun toYaml(context: ProcessingEnvironment): String {

        val result = StringBuilder()

        result.append(
                typeElement.toYaml(context)
        )

        return result.toString()
    }

    fun getYamlType(): String {
        val result = StringBuilder()

        result.appendln("\tschema:")

        result.appendln("\t\ttype: ")
        result.appendln("\t\t\$ref: ''")

        return result.toString()
    }
}