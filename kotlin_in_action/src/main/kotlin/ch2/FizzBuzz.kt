package ch2

fun fizzBuzz(i: Int): String = when {
    i % 15 == 0 -> "FizzBuzzz "
    i % 5 == 0 -> "Buzz "
    i % 3 == 0 -> "Fizz "
    else -> "$i "
}

fun main() {
    // incremento simples intervalo fechado [1 .. 100]
    for (i in 1..100) {
        print(fizzBuzz(i))
    }
    println("\n----")
    // decremento com step
    for (i in 100 downTo 1 step 2) {
        print(fizzBuzz(i))
    }
    println("\n----")
    // incremento simples intervalo aberto [1 .. 100)
    for (i in 1 until 100) {
        print(fizzBuzz(i))
    }
}