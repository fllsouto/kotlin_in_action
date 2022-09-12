package ch2

class Person(val name: String, var isMarried: Boolean)

fun main(args: Array<String>) {
    val person = Person("Maria", true)
    println("Person: ${person.name} -- ${person.isMarried}")

    person.isMarried = false
    println("Person: ${person.name} -- ${person.isMarried}")
}
