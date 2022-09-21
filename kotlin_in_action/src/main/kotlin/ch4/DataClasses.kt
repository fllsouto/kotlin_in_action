package ch4

class Client(val name: String, val postalCode: Int) {

    override fun hashCode(): Int {
        return name.hashCode() * 31 + postalCode
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client)
            return false
        return name == other.name &&
                postalCode == other.postalCode
    }

    override fun toString(): String {
        return "Client(name=$name, postalCode=$postalCode)"
    }

    fun copy(name: String = this.name, postalCode: Int = this.postalCode): Client {
        return Client(name, postalCode)
    }
}
data class ClientData(val name:String, val postalCode: Int)

fun normalClient() {
    println("\n\nnormalClient")
    val client1 = Client("Fellipe", 13091516)
    // Na implementação padrão vai imprimir algo do tipo ch4.Client@2752f6e2
    // Depois da sobrescrita do toString: Client(name=Fellipe, postalCode=13091516)
    println(client1)

    val client2 = Client("Alice", 54321)
    val client3 = Client("Alice", 54321)
    val client4 = client2

    // Compara valores, semelhante ao equals do Java, na implementação default do equals o resultado será falso
    // Após sobrescrever o equals a comparação será true
    println("Does the clients are equal? ${client2 == client3}")
    println("Does the clients references are equal? ${client2 === client4}") // Compara referências

    val processed = hashSetOf(Client("Alice", 54321))
    // Antes de sobrescrever o hashCode a resposta será falsa, por que o contains utiliza o hashCode baseado no endereço
    // de referência do objeto, e não nos atributos. O contrato do hashCode é que se dois objetos são iguais, eles
    // devem ter o mesmo hashCode
    println("Does the processed set contains Alice? ${processed.contains(Client("Alice", 54321))}")

    val client5 = Client("Bob", 345345)
    val client6 = client5.copy()
    println("Does bob's values are the same? ${client5 == client6}")
    println("Does bob's references are the same? ${client5 === client6}")
}

fun clientWithDataClass() {
    println("\n\nclientWithDataClass")
    val client1 = ClientData("Fellipe", 13091516)
    // Na implementação padrão vai imprimir algo do tipo ch4.Client@2752f6e2
    // Depois da sobrescrita do toString: Client(name=Fellipe, postalCode=13091516)
    println(client1)

    val client2 = ClientData("Alice", 54321)
    val client3 = ClientData("Alice", 54321)
    val client4 = client2

    // Compara valores, semelhante ao equals do Java, na implementação default do equals o resultado será falso
    // Após sobrescrever o equals a comparação será true
    println("Does the clients are equal? ${client2 == client3}")
    println("Does the clients references are equal? ${client2 === client4}") // Compara referências

    val processed = hashSetOf(ClientData("Alice", 54321))
    // Antes de sobrescrever o hashCode a resposta será falsa, por que o contains utiliza o hashCode baseado no endereço
    // de referência do objeto, e não nos atributos. O contrato do hashCode é que se dois objetos são iguais, eles
    // devem ter o mesmo hashCode
    println("Does the processed set contains Alice? ${processed.contains(ClientData("Alice", 54321))}")
}

fun main() {
    normalClient()
    clientWithDataClass()
}