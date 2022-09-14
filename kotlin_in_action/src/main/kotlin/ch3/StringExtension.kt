package ch3

// fun <Tipo-receptor>: <Retorno> = <Objeto-receptor>
fun String.lastChar(): Char = this[this.length - 1]


fun main() {
    println("Ultimo character de Kotlin é: ${"kotlin".lastChar()}")
}