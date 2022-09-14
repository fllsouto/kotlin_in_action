package ch3

fun main() {
    val pair1 = "waka".to(1)
    val pair2 = "foo" to 2 // a função to é do tipo infixa, e para isso em sua declaração recebe o modificador "infix"
    println(pair1)
    println(pair2)

    val (key1, value1) = pair1
    val (key2, value2) = pair2
    println("$key1: $value1")
    println("$key2: $value2")
}