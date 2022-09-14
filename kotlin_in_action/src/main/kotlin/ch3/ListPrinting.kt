package ch3

import java.lang.StringBuilder


fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun <T> Collection<T>.joinToStringCustom(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun Collection<String>.joinStrings(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
) = joinToStringCustom(separator, prefix, postfix)

fun main() {
    val list = listOf(1, 2, 3, 4)
    val strings = listOf("waka", "foo", "bar")
    println(joinToString(list, "; ", "(", ")"))
    println(joinToString(list, ".. ", postfix = "#"))
    println(joinToString(list, ".. ", postfix = "#", prefix = "$"))
    println(list.joinToStringCustom( "x", postfix = "<", prefix = ">"))
    // println(list.joinStrings( "x", postfix = "<", prefix = ">")) // Erro de type mismatch
    println(strings.joinStrings( separator = ";"))
}