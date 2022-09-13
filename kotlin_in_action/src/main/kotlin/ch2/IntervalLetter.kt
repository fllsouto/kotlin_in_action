package ch2

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'

fun recognize(c: Char) = when(c) {
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    in '0'..'9' -> "It's a number!"
    else -> "I don't know..."
}
fun main() {
    println("C isLetter: ${isLetter('C')}")
    println("x isLetter: ${isLetter('x')}")
    println("1 isLetter: ${isLetter('1')}")
    println("1 isNotDigit: ${isNotDigit('1')}")
    println("A isNotDigit: ${isNotDigit('A')}")
    println("x isNotDigit: ${isNotDigit('x')}")
    println("Recognize X: ${recognize('X')}")
    println("Recognize a: ${recognize('a')}")
    println("Recognize 1: ${recognize('1')}")
    println("Recognize ?: ${recognize('?')}")
}