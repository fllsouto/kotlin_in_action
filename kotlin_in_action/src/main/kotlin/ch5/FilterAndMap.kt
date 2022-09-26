package ch5

fun main() {
    val list = listOf(1,2,3,4,5,6,7,8,9,10)
    println(list.filter { it % 2 == 0 })
    println(list.map { it * it })
    println(list.filter { it % 3 == 0 }.map { it * it })

    val numbers = mapOf(0 to "zero", 1 to "one", 2 to "two", 3 to "three")
    println(numbers.mapValues { it.value.uppercase() })
    println(numbers.mapKeys { it.key.toString().hashCode() })
    println("Has any square number greater than 50? ${list.map { it * it }.count { it >= 50 }}")
    println("All squares are even? ${list.map { it * it }.all { it % 2 == 0 }}")
    println("All double numbers are even? ${list.map { it * 2 }.all { it % 2 == 0 }}")
    println("Any number can be divided by 3? ${list.any { it % 3 == 0 }}")
    println("Find a number that can be divided by 3: ${list.map { it * it }.find { it % 3 == 0 }}")
}