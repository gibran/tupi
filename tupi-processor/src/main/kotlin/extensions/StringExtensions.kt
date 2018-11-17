package tupi.processor.extensions

import com.google.common.base.CaseFormat

fun String.replaceAll(source : String, target: String) : String {
    return this.replace(Regex(source), target)
}

fun String.toSnakeCase(): String {
    return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this)
}