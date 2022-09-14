package ch3

fun main() {
    // Só "Array" pode ser spreaded aparentemente
    val list1 = arrayOf("waka", "foo", "bar")
    val list2 = listOf("wololo", *list1)
    println(list2)
}