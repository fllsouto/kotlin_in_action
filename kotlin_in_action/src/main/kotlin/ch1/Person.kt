package ch1

data class Person(val name: String, val age: Int? = null)

fun main(args: Array<String>) {
    val persons = listOf(Person("Alice"), Person("Bob", age = 29))
    val oldest = persons.maxBy { it.age ?: 0 } // Elvis-operator: Retorna age se existe, e zero se for null
    println("The oldest s: $oldest")
}