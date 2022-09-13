package ch2

import java.io.BufferedReader
import java.io.StringReader
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException


fun throwingException(number: Int) { // A classe é Int, não Integer!!!!!!
    val percentage = if (number in 0..100) {
        number
    } else {
        throw IllegalArgumentException("A percentage value must be between 0 and 100: $number")
    }

    println("The percentage is $percentage%")
}

fun readNumber(reader: BufferedReader): Int? {
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        println(e.toString())
        return null
    } finally {
        reader.close()
    }
}

fun readNumberExpression(reader: BufferedReader)  {
    val number = try {
        val line = reader.readLine()
        Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        println(e.toString())
        null
    }
    println("Parsed number: $number")
}

fun main() {
    throwingException(74)
//    throwingException(-1)

    val reader = BufferedReader(StringReader("239"))
    println(readNumber(reader))

    val reader2 = BufferedReader(StringReader("foo"))
    println(readNumber(reader2))

    val reader3 = BufferedReader(StringReader("42"))
    readNumberExpression(reader3)

    val reader4 = BufferedReader(StringReader("bar"))
    readNumberExpression(reader4)
}