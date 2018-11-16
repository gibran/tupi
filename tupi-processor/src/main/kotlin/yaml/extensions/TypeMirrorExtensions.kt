package tupi.processor.yaml.extensions

import tupi.processor.extensions.toString
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.TypeElement
import javax.lang.model.type.ArrayType
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.TypeKind
import javax.lang.model.type.TypeMirror

private var templateRef = "\$ref: '#/definitions/"

fun TypeMirror.isKotlinType(context: ProcessingEnvironment): Boolean {

    if (this.kind.isPrimitive)
        return true

    if (this.isCollection())
        return false

    //Verify is a kotlin class
    val element = context.typeUtils.asElement(this) ?: return false
    val typeElement = element as TypeElement
    val metaDataClass = Class.forName("kotlin.Metadata").asSubclass(Annotation::class.java)
    return typeElement.getAnnotation(metaDataClass) == null
}

fun TypeMirror.isCollection(): Boolean {
    if (this.kind == TypeKind.ARRAY)
        return true

    if (this is DeclaredType
            && this.asElement().simpleName.toString() == "List")
        return true

    return false
}

private fun parseCollectionToYaml(innerType: TypeMirror, context: ProcessingEnvironment): String {

    val result = StringBuilder()

    result.appendln("type: array")
            .appendln("items:")

    if (innerType.isKotlinType(context))
        result.append(innerType.toYaml(context))
    else {
        result.append("\t" +
                writeReferenceType(innerType as DeclaredType)
        )
    }

    return result.toString()
}

fun TypeMirror.toYaml(context: ProcessingEnvironment): String {
    val result = StringBuilder()

    if (!this.isKotlinType(context) && !this.isCollection() && this.kind == TypeKind.DECLARED) {
        result.append(writeReferenceType(this as DeclaredType))
    } else {

        when (this.kind) {
            TypeKind.INT -> result.appendln("type: integer").append("format: int32")
            TypeKind.LONG -> result.appendln("type: integer").append("format: int64")
            TypeKind.FLOAT -> result.appendln("type: number").append("format: float")
            TypeKind.DOUBLE -> result.appendln("type: number").append("format: double")
            TypeKind.BYTE -> result.appendln("type: string").append("format: byte")
            TypeKind.BOOLEAN -> result.appendln("type: boolean")
            TypeKind.CHAR -> result.appendln("type: string")
            TypeKind.ARRAY -> result.append(parseCollectionToYaml(getInnerCollectionType(this), context))
            else -> {
                if (this.isCollection()) {
                    result.append(parseCollectionToYaml(getInnerCollectionType(this), context))
                } else {
                    result.append("type: string")
                }
            }
        }
    }

    return result.toString(lineIdent = "\t")
}

private fun getInnerCollectionType(target: TypeMirror): TypeMirror {
    if (target.kind == TypeKind.ARRAY)
        return (target as ArrayType).componentType

    return (target as DeclaredType).typeArguments.first()
}

private fun writeReferenceType(target: DeclaredType): String {
    val refName = target.asElement().simpleName
    return "$templateRef$refName'"
}