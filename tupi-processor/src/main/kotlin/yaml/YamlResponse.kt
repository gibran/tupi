package tupi.processor.yaml

import tupi.annotations.SwaggerResponse
import tupi.processor.yaml.extensions.isPrimitiveTypes
import javax.lang.model.element.TypeElement

class YamlResponse (private val classType : TypeElement, private val response : SwaggerResponse) {

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

            if (!classType.isPrimitiveTypes())
                result.append("\t\t\t\t\t\t\t\$ref: '#/definitions/${classType.simpleName}'")
            else
                result.append("\t\t\t\t\t\t\ttype: ${classType.simpleName.toString().toLowerCase()}")

        }

        return result.toString()
    }
}