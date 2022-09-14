package ch3


//val StringBuilder.lastChar: Char // A 'val'-property cannot have a setter
var StringBuilder.lastChar: Char
    get() = this[length - 1]
    set(value: Char) {
        this[length - 1] =  value
    }

fun main() {
    println("Kotlin".lastChar())

    val sb = StringBuilder("Kotlin!")
    println(sb.lastChar)
    sb.lastChar = '?' // '' single quotes to char, double quotes to string
    println(sb.lastChar)
    println(sb.toString())
}