package ch3

fun main() {
    val set = hashSetOf(1, 7, 53)
    val list = arrayListOf(1, 7, 42)
    val map = hashMapOf(1 to "one", 7 to "seven", 11 to "eleven")

    // São todas classes do java
    println("set.javaClass: ${set.javaClass}")
    println("list.javaClass: ${list.javaClass}")
    println("map.javaClass: ${map.javaClass}")

    println("set max: ${set.max()}")
    println("list last: ${list.last()}")
}