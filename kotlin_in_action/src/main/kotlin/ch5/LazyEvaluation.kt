package ch5



data class Book2(val title: String)

fun main() {
    val books = booksTitle.map { title: String -> Book2(title)}
    println(books)

//    println("\n------Books list------")
//    val booksListStartingWithH = books.map(Book2::title).filter { println("processing $it ..."); it.startsWith("D") }
//    println(booksListStartingWithH)

//    println("\n------Books seq------")
//    val booksSeqStartingWithH = books.asSequence().map(Book2::title).filter { println("processing $it ..."); it.startsWith("D") }.toList()
//    println(booksSeqStartingWithH)

    // Mapping Eager
    println("\n------Books list searching------")
    val bookStartingWithPFromList = books.map {it: Book2 -> println("mapping $it ..."); it.title}.find { println("searching $it ..."); it.startsWith("D") }
    println(bookStartingWithPFromList)

    // Mapping Lazy
    println("\n------Books seq searching------")
    val bookStartingWithPFromSeq = books.asSequence().map {it: Book2 -> println("mapping $it ..."); it.title}.find { println("searching $it ..."); it.startsWith("D") }
    println(bookStartingWithPFromSeq)

}


val booksTitle = listOf(
    "Fundamentals of Wavelets",
    "Data Smart",
    "God Created the Integers",
    "Superfreakonomics",
    "Orientalism",
    "Nature of Statistical Learning Theory, The",
    "Integration of the Indian States",
    "Drunkard's Walk, The",
    "Image Processing & Mathematical Morphology",
    "How to Think Like Sherlock Holmes",
    "Data Scientists at Work",
    "Slaughterhouse Five",
    "Birth of a Theorem",
    "Structure & Interpretation of Computer Programs",
    "Age of Wrath, The",
    "Trial, The",
    "Statistical Decision Theory'",
    "Data Mining Handbook",
    "New Machiavelli, The",
    "Physics & Philosophy",
    "Making Software",
    "Analysis, Vol I",
    "Machine Learning for Hackers",
    "Signal and the Noise, The",
    "Python for Data Analysis",
    "Introduction to Algorithms",
    "Beautiful and the Damned, The",
    "Outsider, The",
    "Complete Sherlock Holmes, The - Vol I",
    "Complete Sherlock Holmes, The - Vol II",
    "Wealth of Nations, The",
    "Pillars of the Earth, The",
    "Mein Kampf",
    "Tao of Physics, The",
    "Surely You're Joking Mr Feynman",
    "Farewell to Arms, A",
    "Veteran, The",
    "False Impressions",
    "Last Lecture, The"
)