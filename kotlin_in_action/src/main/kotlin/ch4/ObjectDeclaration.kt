package ch4

import ch2.Person
import java.io.File

object Payroll {
    val allEmployees = arrayOf<Person>()
    fun calculateSalary() {
        for (person in allEmployees) {
            // lógica
        }
    }
}

object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(file1: File?, file2: File?): Int {
        if (file1 == null || file2 == null) {
            return -1
        }
        return file1.path.compareTo(file2.path, ignoreCase = true)
    }
}

data class PersonComparator(val name: String) {
    object NameComparator : Comparator<PersonComparator> {
        override fun compare(p1: PersonComparator?, p2: PersonComparator?): Int {
            if (p1 == null || p2 == null) {
                return -1
            }
            return p1.name.compareTo(p2.name)
        }

    }
}

fun main() {
    val result = CaseInsensitiveFileComparator.compare(File("/User"), File("/user"))
    println("Comparator result: $result")

    val files = listOf<File>(File("/Z"), File("/y"), File("/X"))
    println(files.sortedWith(CaseInsensitiveFileComparator))

    val persons = listOf(PersonComparator("Bob"), PersonComparator("Alice"), PersonComparator("Charles"))
    println(persons.sortedWith(PersonComparator.NameComparator))
}