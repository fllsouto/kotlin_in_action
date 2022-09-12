package ch2

import java.lang.IllegalArgumentException

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun main() {
    val expr = Sum(Sum(Num(1), Num(2)), Num(4))
    println("Evaluated Java Style: ${evalJavaStyle(expr)}")
    println("Evaluated Kotlin Style V1: ${evalKotlinStyleV1(expr)}")
    println("Evaluated Kotlin Style V2: ${evalKotlinStyleV2(expr)}")
    println("Evaluated Kotlin Style V3: ${evalKotlinStyleV3(expr)}")
}

fun evalJavaStyle(e: Expr): Int {
    if (e is Num) {
        // val n = e as Num // Podemos fazer o cast explĩcito assim
        //  return n.value //smartcast background color
        return e.value //smartcast background color
    }
    if (e is Sum) {
        return evalJavaStyle(e.left) + evalJavaStyle(e.right)
    }
    throw IllegalArgumentException("Unknown expression")
}

fun evalKotlinStyleV1(e: Expr): Int =
    if (e is Num) {
        e.value
    } else if (e is Sum) {
        evalKotlinStyleV1(e.left) + evalKotlinStyleV1(e.right)
    } else {
        throw IllegalArgumentException("Unknown expression")
    }

fun evalKotlinStyleV2(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> evalKotlinStyleV2(e.left) + evalKotlinStyleV2(e.right)
        else -> throw IllegalArgumentException("Unknown expression")
    }

fun evalKotlinStyleV3(e: Expr): Int =
    when (e) {
        is Num -> {
            println("num: ${e.value}")
            e.value
        }
        is Sum -> {
            val left = evalKotlinStyleV3(e.left)
            val right = evalKotlinStyleV3(e.right)
            println("sum: $left + $right")
            left + right
        }
        else -> throw IllegalArgumentException("Unknown expression")
    }