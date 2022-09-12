package ch2

// fun é uma palavra reservada
// <nome-do-parametro>: TipoDoParametro
// A função não precisa estar em uma classe, pode estar no nível mais alto de um arquivo
// existem muitos wrappers em torno de funções da biblioteca-padrão, como por exemplo println
// podemos omitir o ; no final das linhas
fun main(args: Array<String>) {
    println("Hello World!")
    val max = max(10, 42);
    val min = min(10, 42)
    println("Max value: $max")
    println("Min value: $min")

    // min = min(18, 49) // Val cannot be reassigned

    var min2 = min(10, 42)
    println("Min value: $min2")

    min2 = min(18, 49)
    println("Min value: $min2")

    val languages = arrayListOf("Java")
    // Adicionar um elemento a uma coleção não é considerado quebra da imutabilidade
    languages.add("kotlin")
    println("Languages array: $languages")

    //languages = arrayListOf("cobol") // Val cannot be reassigned
}

// função com corpo de bloc
fun max(a: Int, b: Int): Int {
    return if(a > b) a else b
}

// função com corpo de expressão, pode-se omitir o tipo do retorno
fun min(a: Int, b: Int): Int = if (a > b) b else a