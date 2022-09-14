package ch3


fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullname = path.substringAfterLast("/")
    val filename = fullname.substringBeforeLast(".")
    val extension = fullname.substringAfterLast(".")
    println("Dir: $directory, name: $filename, ext: $extension")
}

fun parsePathWithRegex(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured
        println("Dir: $directory, name: $filename, ext: $extension")
    }
}
fun main() {
    val path = "/home/fllsouto/Workspace/study/kotlin_study/README.md"
    parsePath(path)
    parsePathWithRegex(path)
}