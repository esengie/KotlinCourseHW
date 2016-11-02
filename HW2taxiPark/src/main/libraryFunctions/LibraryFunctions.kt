package libraryFunctions

fun main(args: Array<String>) {
    functionsOnStrings()
    separator()
    otherUsefulFunctions()
    separator()
    plusMinusOnCollections()
}

fun separator() {
    println("------------------")
}

fun functionsOnStrings() {
    val s = "abCDa"

    println(s.filter { it in 'a'..'z' }) // aba
    println(s.map { it.toInt() })  // [97, 98, 67, 68, 97]

    println(s.maxBy { it.toInt() }) // b

    println(s.count { it == 'a' }) // 2
    println(s.any { it == 'a' })   // true
    println(s.all { it == 'a' })   // false
    println(s.none { it == 'a' })  // false
    println(s.find { it in 'A'..'Z' }) // C

    s.withIndex().forEach {
        print("${it.index}${it.value}")  // 0a1b2C3D4a
    }
    println()

    val (lowerCase, upperCase) = s.partition { it in 'a'..'z' }
    println(lowerCase)    // aba
    println(upperCase)    // CD

    // the similar functions can be called on collections
}

data class Product(val name: String, val price: Double) {
    override fun toString() = name
}

fun otherUsefulFunctions() {
    val products = listOf(Product("p1", 12.3), Product("p2", 0.2), Product("p3", 8.8))

    println(products.sumByDouble { it.price })  // 21.3

    println(products.sortedBy { it.price })             // [p2, p3, p1]
    println(products.sortedByDescending { it.price })   // [p1, p3, p2]
}

fun plusMinusOnCollections() {
    val list = listOf(1, 2, 3, 4)
    println(list + 5)                 // [1, 2, 3, 4, 5]
    println(list + listOf(6, 7))      // [1, 2, 3, 4, 6, 7]
    println(list - 3)                 // [1, 2, 4]
    println(list - listOf(1, 2))      // [3, 4]
}