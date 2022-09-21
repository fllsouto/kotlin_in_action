package ch4

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}

// public' member exposes its 'internal' receiver type TalkativeButton
// funções de extensão não tem acesso aos membros private e protected
/*
fun TalkativeButton.giveSpeech() {
    yell()
    whisper() // Só será visivel para a própria classe e para as subclasses, mais simples que Java
}*/

class LengthCounter {
    var counter: Int = 0
        private set
    fun addWord(word: String) {
        counter += word.length
    }
}

fun main() {
    val lengthCounter = LengthCounter()
    lengthCounter.addWord("Hello ")
    println(lengthCounter.counter)
    lengthCounter.addWord("World!")
    println(lengthCounter.counter)
}