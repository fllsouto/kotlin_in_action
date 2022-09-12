package ch2

import ch2.Color.*
import java.lang.Exception

fun getWarmth(color: Color) = when(color) {
    RED, ORANGE, YELLOW -> "warn"
    GREEN -> "neutral"
    BLUE, INDIGO,Color.VIOLET -> "cold"
}

// Não otimizada, cria vários sets só para fazer a comparação
fun mix(c1: Color, c2: Color) = when(setOf(c1, c2)) {
    setOf(RED, YELLOW) -> ORANGE
    setOf(YELLOW, BLUE) -> GREEN
    setOf(BLUE, VIOLET) -> INDIGO
    else -> throw Exception("Dirty color")
}

// Versão mais otimizada, porém menos legível
fun mixOptimized(c1: Color, c2: Color) = when {
    (c1 == RED && c2 == YELLOW) ||
    (c2 == RED && c1 == YELLOW) ->
        ORANGE
    (c1 == YELLOW && c2 == BLUE) ||
    (c2 == YELLOW && c1 == BLUE) ->
        GREEN
    (c1 == BLUE && c2 == VIOLET) ||
    (c2 == BLUE && c1 == VIOLET) ->
        INDIGO
    else -> throw Exception("Dirty color")
}

fun main() {
    println("Color warmth: ${getWarmth(RED)}")
    println("Color warmth: ${getWarmth(BLUE)}")
    println("Color warmth: ${getWarmth(GREEN)}")

    println("Mixture: ${mix(RED, YELLOW)}")
    println("Mixture: ${mix(BLUE, YELLOW)}")
    println("Mixture: ${mix(BLUE, VIOLET)}")
    println("Mixture: ${mix(GREEN, YELLOW)}")
}