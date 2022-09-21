package ch4

class A {
    companion object {
        fun bar() {
            println("Companion object called")
        }
    }
}

class UserWithoutCompanion {
    val nickname: String
    constructor(email: String) {
        nickname = email.substringBefore("@")
    }
    constructor(facebookAccountId: Int) {
        nickname = getFacebookName(facebookAccountId)
    }

    private fun getFacebookName(facebookAccountId: Int): String {
        return facebookAccountId.toString()
    }
}

class UserWithCompanion(val nickname: String) {
    companion object {

        fun newSubscribingUser(email: String): UserWithCompanion {
            return UserWithCompanion(email.substringBefore("@"))
        }
        fun newFacebookUser(facebookAccountId: Int): UserWithCompanion {
            return UserWithCompanion(getFacebookName(facebookAccountId))
        }

        private fun getFacebookName(facebookAccountId: Int): String {
            return facebookAccountId.toString()
        }
    }
}


fun main() {
    A.bar()
    val subscribingUser1 = UserWithoutCompanion("teste@gmail.com")
    val facebookUser1 = UserWithoutCompanion(107409823)

    val subscribingUser2 = UserWithCompanion.newSubscribingUser("teste2@gmail.com")
    val facebookUser2 = UserWithCompanion.newFacebookUser(107409823)
}