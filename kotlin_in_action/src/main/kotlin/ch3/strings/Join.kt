// Podemos modificar o nome da classe gerada através da seguinte anotação:
//@file:JvmName("WololoWakaFooBar")
package ch3.strings

import java.lang.StringBuilder

// Essa função será compilada para um método estático da classe JoinKt, uma classe criada automaticamente
fun <T> joinToString( // Essa é uma função de nível superior
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}
