package tupi.processor.yaml.extensions

import tupi.processor.extensions.toString
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement

fun TypeElement.toYaml(context: ProcessingEnvironment): String {
    val result = StringBuilder()

    result.append(
            writeTypeElement(this, context)
    )

    return result.toString(lineIdent = "\t")
}

fun TypeElement.isPrimitiveTypes(): Boolean {
    val metaDataClass = Class.forName("kotlin.Metadata").asSubclass(Annotation::class.java)

    return this.getAnnotation(metaDataClass) == null

//    when (this) {
//        Int::class -> return true
//        Long::class -> return true
//        Float::class -> return true
//        Double::class -> return true
//        Byte::class -> return true
//        Boolean::class -> return true
//        Date::class -> return true
//        String::class -> return true
//    }
//
//    return false
}

private fun writeTypeElement(target: TypeElement, context: ProcessingEnvironment): String {

    val result = StringBuilder()

    result.appendln("${target.simpleName}:")
            .appendln("\ttype: object")
            .append(toYamlDefinition(target, context))

    return result.toString()
}

private fun toYamlDefinition(target: TypeElement, context: ProcessingEnvironment): String {

    val properties = StringBuilder().appendln("properties:")
    val requiredProperties = StringBuilder().appendln("required:")

    target.enclosedElements.filter { it.kind == ElementKind.FIELD }
            .forEach { property: Element ->

                if (isRequired(property))
                    requiredProperties.appendln("- ${property.simpleName}")

                properties.appendln(writeProperty(property, context))
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

private fun writeProperty(property: Element, context: ProcessingEnvironment): String {
    val result = StringBuilder()

    val typeMirror = property.asType()

    result.appendln("${property.simpleName}:")
            .append(typeMirror.toYaml(context)) //TODO: Impelementar


    return result.toString(lineIdent = "\t")
}

