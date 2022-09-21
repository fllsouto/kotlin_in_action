package ch4

interface Context
interface AttributeSet

open class ViewOpen {
    constructor(ctx: Context) {
        // código
        println("Construtor ViewOpen 1")
    }

    constructor(ctx: Context, attr: AttributeSet) {
        println("Construtor ViewOpen 2")
        // código
    }

    constructor() {
        println("Construtor ViewOpen 3")
        // código
    }
}

class MyButton : ViewOpen {
    // Caso eu náo adicione o super temos o erro: Explicit 'this' or 'super' call is required. There is no
    // constructor in superclass that can be called without arguments
    constructor(ctx: Context): super(ctx) {
        println("Construtor MyButton 1")
        // código
    }

    constructor(ctx: Context, attr: AttributeSet): super(ctx, attr) {
        println("Construtor MyButton 2")
        // código
    }

    constructor(attr: AttributeSet): super() {
        println("Construtor MyButton 3")
        // código
    }

    // Podemos chamar outro construtor da mesma classe
    constructor(ctxList: List<Context>): this(ctxList[0]) {
        println("Construtor MyButton 4")
        // código
    }
}
