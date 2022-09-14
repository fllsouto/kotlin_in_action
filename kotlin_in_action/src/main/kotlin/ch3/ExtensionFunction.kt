package ch3

class User(val id: Int, val name: String, val address: String)
fun saveUserV1(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty Name")
    }
    if (user.address.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty Address")
    }
    println("User saved! Id: ${user.id}")
}

fun saveUserV2(user: User) {
    fun validate(user: User, value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${user.id}: empty $fieldName")
        }
    }
    validate(user, user.name, "Name")
    validate(user, user.address, "Addess")
    println("User saved! Id: ${user.id}")
}

fun saveUserV3(user: User) {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${user.id}: empty $fieldName")
        }
    }
    validate(user.name, "Name")
    validate(user.address, "Address")
    println("User saved! Id: ${user.id}")
}

fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user $id: empty $fieldName")
        }
    }
    validate(name, "Name")
    validate(address, "Addess")
    println("User saved! Id: $id")
}

fun saveUser(user: User) {
    user.validateBeforeSave()
}



fun main() {
//    saveUserV1(User(1, "", ""))
//    saveUserV2(User(1, "", ""))
//    saveUserV3(User(1, "", ""))
//    saveUser(User(1, "", ""))
    saveUser(User(1, "Fellipe Souto", "Rua X, 42"))
}
