package tupi.processor.yaml.extensions

import tupi.processor.extensions.toString
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.type.TypeKind

fun TypeElement.toYaml(): String {
    val result = StringBuilder()

    result.appendln(
        writeClass(this)
    )

    return result.toString(lineIdent = "\t")
}

private fun writeClass(target: TypeElement): String {

    val result = StringBuilder()

    result.appendln("${target.simpleName}:")
        .append("\t")
        .appendln("type: object")
        .append(parseClassToYaml(target))

    return result.toString()
}

private fun writeProperty(property: Element): String {
    val result = StringBuilder()

    result.appendln("${property.simpleName}:")

    when (property.asType().kind) {
        TypeKind.INT -> result.appendln("\ttype: integer").append("\tformat: int32")
        TypeKind.LONG -> result.appendln("\ttype: integer").append("\tformat: int64")
        TypeKind.FLOAT -> result.appendln("\ttype: number").append("\tformat: float")
        TypeKind.DOUBLE -> result.appendln("\ttype: number").append("\tformat: double")
        TypeKind.BYTE -> result.appendln("\ttype: string").append("\tformat: byte")
        TypeKind.BOOLEAN -> result.appendln("\ttype: boolean")
        TypeKind.CHAR -> result.appendln("\ttype: string")
        TypeKind.ARRAY -> result.appendln("\ttype: array").appendln("\titems: ").append("\t\ttype: string") //TODO: Tratar tipos
        else -> {
            result.append("\ttype: string") //TODO: Tratar tipos
        }
    }

    return result.toString(lineIdent = "\t")
}

private fun parseClassToYaml(target: TypeElement): String {

    val properties = StringBuilder().appendln("properties:")
    val requiredProperties = StringBuilder().appendln("required:")

    target.enclosedElements.filter { it.kind == ElementKind.FIELD }
        .forEach { property: Element ->

            val isNullable =
                property.annotationMirrors.any { it.annotationType.toString() == "org.jetbrains.annotations.Nullable" }

            if (!isNullable)
                requiredProperties.appendln("- ${property.simpleName}")

            properties.appendln(writeProperty(property))
        }

    val result = StringBuilder()

    result.append(requiredProperties.toString(lineIdent = "\t"))
        .append(properties.toString(lineIdent = "\t"))

    return result.toString()
}