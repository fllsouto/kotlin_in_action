package ch2

import java.util.TreeMap

fun main() {
    val binaryReps = TreeMap<Char, String>() // Não precisa utilizar a palavra new para instanciar
    for (c in 'A' .. 'F') {
        val binary = Integer.toBinaryString(c.code) //toInt() está deprecado
        binaryReps[c] = binary
    }
    for ((letter, binary) in binaryReps) { // desempacotamento de elementos
        println("$letter = $binary")
    }

    val list = listOf("000", "001", "010", "011", "100")
    for ((index, element) in list.withIndex()) {
        println("[$index] = $element")
    }
}