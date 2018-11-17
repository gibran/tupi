package tupi.processor.yaml

import tupi.processor.extensions.toYaml
import javax.lang.model.element.TypeElement

internal class YamlDefinition(private val typeElement: TypeElement) {

    fun toYaml(definitions: HashMap<String, String>): String {

        val result = StringBuilder()

        result.append(
                typeElement.toYaml(definitions)
        )

        return result.toString()
    }
}