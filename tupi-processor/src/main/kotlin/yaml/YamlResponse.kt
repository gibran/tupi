package tupi.processor.yaml

import tupi.annotations.SwaggerResponse
import tupi.processor.extensions.toString
import tupi.processor.extensions.toYaml
import javax.lang.model.element.Element

class YamlResponse(private val classType: Element, private val response: SwaggerResponse) {

    fun toString(lineIdent: String): String {
        val result = StringBuilder()

        result.appendln("${response.status}:")
                .append("\tdescription: '${response.description}'")

        if (classType.simpleName.toString() != "Unit") {
            result.appendln("")
                    .appendln("\tcontent:")
                    .appendln("\t\tapplication/json:")
                    .appendln("\t\t\tschema:")

            result.append(classType.asType().toYaml().toString("\t\t\t"))
        }

        return result.toString(lineIdent = lineIdent)
    }
}