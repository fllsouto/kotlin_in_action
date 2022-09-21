package ch4

class CountingSet<T>(val innerSet: MutableCollection<T> = HashSet<T>()) : MutableCollection<T> by innerSet {
// Sem a delegação eu teria que implementar todos esse métodos
//    override val size: Int
//        get() = TODO("Not yet implemented")
//
//    override fun clear() {
//        TODO("Not yet implemented")
//    }
//
//    override fun addAll(elements: Collection<T>): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun add(element: T): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun isEmpty(): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun iterator(): MutableIterator<T> {
//        TODO("Not yet implemented")
//    }
//
//    override fun retainAll(elements: Collection<T>): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun removeAll(elements: Collection<T>): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun remove(element: T): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun containsAll(elements: Collection<T>): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun contains(element: T): Boolean {
//        TODO("Not yet implemented")
//    }

    var workDone = 0
    override fun addAll(elements: Collection<T>): Boolean {
        workDone += elements.size * 2
        return innerSet.addAll(elements)
    }

    override fun add(element: T): Boolean {
        workDone += 2
        return innerSet.add(element)
    }
}

fun main() {
    val cset = CountingSet<Int>()
    cset.addAll(listOf(1,2,3))
    println("${cset.workDone} work done, ${cset.size} size")
}