package tupi.processor.extensions

import java.util.regex.Pattern

fun String.replaceAll(source : String, target: String) : String {
    return this.replace(Regex(source), target)
}

fun String.toSnakeCase(): String {
    var text = ""
    var isFirst = true
    this.forEach {
        if (it.isUpperCase()) {
            if (isFirst) isFirst = false
            else text += "_"
            text += it.toLowerCase()
        } else {
            text += it
        }
    }
    return text

    //return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this)
}

fun String.toString(lineIdent : String) : String{

    val pattern = Pattern.compile("(?m)(^)")
    val matcher = pattern.matcher(this)

    val result = StringBuilder()
    result.append(matcher.replaceAll(lineIdent))

    return result.toString()
}