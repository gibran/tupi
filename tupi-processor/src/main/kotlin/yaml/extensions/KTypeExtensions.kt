package tupi.processor.yaml.extensions

import tupi.processor.extensions.toString
import java.util.*
import javax.lang.model.element.TypeElement
import javax.lang.model.type.TypeMirror

private var templateRef = "\$ref: '#/definitions/"

private fun parseCollectionToYaml(returnType: TypeElement) : String {

    val result = StringBuilder()

    result.appendln("type: array").appendln("items:")

//    val innerType = returnType.asClassName().toString()
//    val innerTypeName = innerType.simpleName
//
//    if (innerType.isPrimitiveTypes())
//        result.append("\ttype: $innerTypeName")
//    else
//        result.append("\t$templateRef$innerTypeName'")

    return result.toString()
}

fun TypeElement.isPrimitiveTypes() : Boolean {
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

fun TypeElement.isCollection() : Boolean {

    when (this) {
        List::class -> return true
        ArrayList::class -> return true
        MutableList::class -> return true
    }

    return false
}

fun TypeMirror.toYaml() : String {

    val result = StringBuilder()
//
//    if (!this.isPrimitiveTypes() && !this.isCollection()) {
//        val refName = this.simpleName
//        result.append("$templateRef$refName'")
//    }
//    else {
//        when (this) {
//            Int::class -> result.appendln("type: integer").append("format: int32")
//            Long::class -> result.appendln("type: integer").append("format: int64")
//            Float::class -> result.appendln("type: number").append("format: float")
//            Double::class -> result.appendln("type: number").append("format: double")
//            Byte::class -> result.appendln("type: string").append("format: byte")
//            Date::class -> result.appendln("type: string").append("format: date")
//            Boolean::class -> result.append("type: boolean")
//            String::class -> result.append("type: string")
//            ArrayList::class -> result.append(parseCollectionToYaml(this))
//            List::class -> result.append(parseCollectionToYaml(this))
//        }
//    }

    return result.toString(lineIdent = "\t")
}