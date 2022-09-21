package ch4

// val quer dizer que a propriedade correspondente é
// gerada para o parâmetro do construtor
class User(val nickname: String)
class UserWithInit constructor(_nickname: String) {
    val nickname: String
    init {
        nickname = _nickname
    }
}

class UserWithInitImplicit(_nickname: String) {
    val nickname: String = _nickname
}

class UserWithDefaultValues(val nickname: String, val isSubscribed: Boolean = true)

open class UserOpen(val nickname: String, val isSubscribed: Boolean = true)

class TwitterUser(nickname: String): UserOpen(nickname) { /*...*/ }

open class ButtonOpen

class RadioButton: ButtonOpen()

open class Secretive private constructor() { /*...*/}

// Isso gera um erro: Cannot access '<init>': it is private in 'Secretive'
// class SecretiveChild: Secretive()

