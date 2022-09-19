package ch4

import java.io.Serializable

interface State : Serializable
interface View {
    fun getCurrentState(): State
    fun restoreState(state: State)
}

class ButtonV2 : View {
    override fun getCurrentState() = ButtonState()
    override fun restoreState(state: State) { /*...*/ }
    class ButtonState : State { /*...*/ }
}

class Outer {
    inner class Inner {
        fun getOuterReference(): Outer = this@Outer
    }

    class Nested {
        // Não temos uma referencia para a classe externa
        //fun getOuterReference(): Outer = this@Outer
    }
}