package tupi.processor.extensions

fun String.replaceAll(source : String, target: String) : String {
    return this.replace(Regex(source), target)
}