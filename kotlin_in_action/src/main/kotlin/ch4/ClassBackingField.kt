package ch4

class UserBackingField(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            println("""
                Address was changed for $name
                "$field" -> "$value".""".trimIndent())
            field = value
        }
}

fun main() {
    val user = UserBackingField("Alice")
    user.address = "Elsenheimerstrasse 47, 80687 Muechen"
}