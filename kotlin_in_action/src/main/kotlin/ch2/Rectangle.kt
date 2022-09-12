package ch2

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() {
            return height == width
        }
}

fun main() {
    val rect1 = Rectangle(10, 20)
    println("Rectangle 1 is a square? ${rect1.isSquare}")
    val rect2 = Rectangle(20, 20)
    println("Rectangle 2 is a square? ${rect2.isSquare}")
}