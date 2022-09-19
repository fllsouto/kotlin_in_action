package ch4

interface Clickable {
    fun click()
    // Diferente do java, um método declarado em uma classe que tem uma
    // implementação padrão não precisa do modificador default
    fun showOff() = println("I'm clickable!")
}

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus")
    // Isso daqui gerará o seguinte erro: Class 'Button' must override public open fun showOff(): Unit
    // defined in ch4.Clickable because it inherits multiple interface methods of it
    fun showOff() = println("I'm focusable!")
}

// os dois pontos substitui as palavras extends e implements
// Uma classe pode implementar multiplas interfaces, mas estender apenas uma classe
class Button : Clickable, Focusable {
    // Diferente da anotação @Override, esse modificador é obrigatório
    override fun click() = println("I was clicked")

    // Resolvemos isso da seguinte forma
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

fun main() {
    val button = Button()
    button.showOff()
    button.setFocus(true)
    button.click()
}