package ch4

open class RichButton : Clickable {
    fun disable() {}
    open fun animate() {}
    // A sobrescrita torna o método open por padrão, para evitar isso colocamos o modificador "final"

    final override fun click() {}
}

abstract class Animated {
    // Uma classe/método abstrato é open por padrão, para permitir a sobrescrita
    abstract fun animate()
    open fun stopAnimating() {}
    fun animateTwice() {}
}