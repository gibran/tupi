package tupi.processor.yaml

import tupi.annotations.SwaggerResponse
import tupi.processor.extensions.toYaml
import javax.lang.model.element.TypeElement

class YamlResponse(private val classType: TypeElement, private val response: SwaggerResponse) {

    override fun toString(): String {
        val result = StringBuilder()

        result.appendln("\t\t\t${response.status}:")
                .append("\t\t\t\tdescription: '${response.description}'")

        if (classType.simpleName.toString() != "Unit") {
            result
                    .appendln("")
                    .appendln("\t\t\t\tcontent: ")
                    .appendln("\t\t\t\t\tapplication/json:")
                    .appendln("\t\t\t\t\t\tschema:")

            result.append("\t\t\t\t\t\t${classType.asType().toYaml()}")
        }

        return result.toString()
    }
}