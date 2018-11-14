package tupi.processor.extensions

import java.util.regex.Pattern


fun StringBuilder.toString(lineIdent : String) : String{

    val pattern = Pattern.compile("(?m)(^)")
    val matcher = pattern.matcher(this.toString())

    val result = StringBuilder()
    result.append(matcher.replaceAll(lineIdent))

    return result.toString()
}