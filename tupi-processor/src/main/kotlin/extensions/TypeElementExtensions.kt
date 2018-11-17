package tupi.processor.extensions

import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.type.DeclaredType

fun TypeElement.toYaml(definitions: HashMap<String, String>) {
    val result = StringBuilder()

    if (definitions.containsKey(this.asType().toString())) return

    result.append(
            writeTypeElement(this, definitions)
    )

    definitions[this.asType().toString()] = result.toString(lineIdent = "\t")
}

private fun writeTypeElement(target: TypeElement, definitions: HashMap<String, String>): String {

    val result = StringBuilder()

    result.appendln("${target.simpleName}:")
            .appendln("\ttype: object")
            .append(toYamlDefinition(target, definitions))

    return result.toString()
}

private fun toYamlDefinition(target: TypeElement, definitions: HashMap<String, String>): String {

    val properties = StringBuilder().appendln("properties:")
    val requiredProperties = StringBuilder().appendln("required:")

    target.enclosedElements.filter { it.kind == ElementKind.FIELD }
            .forEach { property: Element ->

                if (isRequired(property))
                    requiredProperties.appendln("- ${property.simpleName.toString().toSnakeCase()}")

                properties.appendln(writeProperty(property, definitions))
            }

    val result = StringBuilder()

    result.append(requiredProperties.toString(lineIdent = "\t"))
            .append(properties.toString(lineIdent = "\t"))

    return result.toString()
}

private fun isRequired(property: Element): Boolean {
    return !property.annotationMirrors
            .any { it.annotationType.toString() == "org.jetbrains.annotations.Nullable" }
}

private fun writeProperty(property: Element, definitions: HashMap<String, String>): String {
    val result = StringBuilder()

    val typeMirror = property.asType()

    result.appendln("${property.simpleName.toString().toSnakeCase()}:")
            .append(typeMirror.toYaml())

    if (!definitions.containsKey(typeMirror.toString()))
        addRefType(property, definitions)

    return result.toString(lineIdent = "\t")
}

private fun addRefType(property: Element, definitions: HashMap<String, String>) {

    if (!property.asType().isKotlinType()) {
        if (!property.asType().isCollection()) {
            if (property is TypeElement)
                property.toYaml(definitions)

        } else {
            val innerType = property.asType().getInnerCollectionType()
            if (innerType is DeclaredType) {
                addRefType(innerType.asElement(), definitions)
            }
        }
    }

}