package ch2

fun main(args: Array<String>) {
    // o próprio IDE sugeriu trocar args.size > 0 por args.isNotEmpty
    println("Hello ${if (args.isNotEmpty()) args[0] else "someone"}!")
}