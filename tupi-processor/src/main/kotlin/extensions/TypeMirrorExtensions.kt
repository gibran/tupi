package tupi.processor.extensions

import javax.lang.model.type.*

fun TypeMirror.toYaml(): String {
    val result = StringBuilder()

    if (!this.isKotlinType() && !this.isCollection() && this.kind == TypeKind.DECLARED) {
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
            TypeKind.ARRAY -> result.append(parseCollectionToYaml(this))
            else -> {
                if (this.isCollection()) {
                    result.append(parseCollectionToYaml(this))
                } else {
                    result.append("type: string")
                }
            }
        }
    }

    return result.toString(lineIdent = "\t")
}

internal fun TypeMirror.isCollection(): Boolean {
    if (this.kind == TypeKind.ARRAY)
        return true

    if (this is DeclaredType
            && this.asElement().simpleName.toString() == "List")
        return true

    return false
}

internal fun TypeMirror.isKotlinType(): Boolean {

    if (this.kind.isPrimitive)
        return true

    if (this.isCollection())
        return false

    if (this !is DeclaredType)
        return true

    val element = this.asElement()

    val metaDataClass = Class.forName("kotlin.Metadata").asSubclass(Annotation::class.java)
    return element.getAnnotation(metaDataClass) == null
}

internal fun TypeMirror.getInnerCollectionType(): TypeMirror? {
    if (this.kind == TypeKind.ARRAY)
        return (this as ArrayType).componentType
    if (this is DeclaredType)
        return this.typeArguments.first()

    return null
}

private fun parseCollectionToYaml(collectionType: TypeMirror): String {

    val result = StringBuilder()

    val innerType = collectionType.getInnerCollectionType()

    result.appendln("type: array")
            .appendln("items:")
            .append( innerType?.toYaml() )

    return result.toString()
}

private fun writeReferenceType(target: DeclaredType): String {
    val refName = target.asElement().simpleName
    return "\$ref: '#/definitions/$refName'"
}
