package ch4
interface UserInterface {
    val nickname: String
}

interface UserWithEmail {
    val email: String
    // Não estamos armazenando estado na interface, pois não existe backing-field, por isso essa declaração
    // é inteiramente válida
    val emailDomain: String
        get() = email.substringAfter('@')
}

class PrivateUser(override val nickname: String) : UserInterface
class SubscribingUser(val email: String) : UserInterface {
    // Não temos de fato um valor pro nickname, o getter faz o trabalho de computar sempre que é solicitado
    // Sem backing-field
    override val nickname: String
        get(): String {
            println("Calling SubscribingUser nickname getter method")
            return email.substringBefore('@')
        }
}
class FacebookUser(val accountId: Int) : UserInterface {
    // Temos um backing field para armazenar os dados
    override val nickname = getFacebookName(accountId)

    private fun getFacebookName(accountId: Int): String {
        println("Calling FacebookUser getFacebookName method")
        return accountId.toString()
    }
}

fun main() {
    val privateUser = PrivateUser("test_private@kotlinlang.org")
    println(privateUser.nickname)
    val subscribingUser = SubscribingUser("test_subscribing@kotlinlang.org")
    println(subscribingUser.nickname)

}