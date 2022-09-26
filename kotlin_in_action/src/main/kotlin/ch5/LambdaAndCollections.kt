package ch5

data class Person(val name:String, val age: Int)

fun findTheOldest(people: List<Person>) {
    var maxAge = 0
    var theOldest: Person? = null
    for (person in people) {
        if (person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }
    println(theOldest)
}

fun printMessagesWithPrefix(messages: Collection<String>, prefix: String) {
    messages.forEach {
        println("$prefix $it")
    }
}

fun printProblemsCount(responses: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0
    responses.forEach {
        if (it.startsWith("4")) {
            clientErrors++
        } else if (it.startsWith("5")) {
            serverErrors++
        }
    }
    println("$clientErrors client errors, $serverErrors server errors")
}

fun main() {
    val people = listOf(Person("Gregor", 65), Person("Alice", 28), Person("Bob", 31))
    findTheOldest(people)
    println("Finding oldest person with lambda: ${people.maxBy { it.age }}")
    println("Finding oldest person with member reference: ${people.maxBy(Person::age)}")

    val sum = {x: Int, y: Int -> x + y}
    println("Sum of X with Y: ${sum(10, 32)}")

    run { println("Using run lib to execute lambda code") }
    val names1 = people.joinToString(separator = "-", transform = {p: Person -> p.name}) // Move lambda out of parenthesis
    println(names1)
    val names2 = people.joinToString(separator = "#") {p: Person -> p.name}
    println(names2)

    val sum2 = {x: Int, y: Int ->
        println("X: $x -- Y: $y ...")
        x + y
    }
    println("Sum2 of X with Y: ${sum2(10, 32)}")

    val errors = listOf("403 Forbidden", "404 Not Found")
    printMessagesWithPrefix(errors, "Error:")

    val responses = listOf("200 OK", "418 I'm a teapot", "500 Internal Server Error", "403 Forbidden", "404 Not Found")
    printProblemsCount(responses)

}