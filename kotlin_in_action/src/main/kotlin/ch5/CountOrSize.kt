package ch5

data class Book(val title: String, val authors: List<String>)

fun main() {
    val people = listOf(Person("Gregor", 22), Person("Alice", 28), Person("Bob", 31))
    val canBeInClub27 = { p: Person -> p.age <= 27 }
    println(people.filter(canBeInClub27).size) // Cria uma lista intermediária
    println(people.count(canBeInClub27)) // Não cria uma lista intermediária

    val groupOfClub = people.groupBy(canBeInClub27)
    println(groupOfClub)

    val words = listOf("aba", "caba", "deba", "aa", "cafe", "xade", "dexa")
    println(words.groupBy { it[0] })

    val books = listOf(
        Book("Thursday Next", listOf("Jasper Fforde")),
        Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman")),
        Book("Mort", listOf("Terry Pratchett")),
        Book("American Gods", listOf("Neil Gaiman")),
        Book("Fragile Things: Short Fictions and Wonders Vol 1", listOf("Neil Gaiman")),
    )


    println(books.flatMap { it.authors })
    println(books.flatMap { it.authors }.toSet())

    val authors = listOf(
            listOf("Jasper Fforde"),
            listOf("Terry Pratchett", "Neil Gaiman"),
            listOf("Terry Pratchett"),
            listOf("Neil Gaiman"),
            listOf("Neil Gaiman")
    )

    println(authors.flatten())
}