package lambdas

fun basicSyntax() {
    // лямбда всегда находится в фигурных скобках
    val sum = { x: Int, y: Int -> x + y }

    // переменная sum имеет функциональный тип, его можно выписать явно
    // (и тогда компилятор может вывести типы параметров лямбды)
    val sum1: (Int, Int) -> Int = { x, y -> x + y }

    // переменную функционального типа можно вызвать как функцию
    println(sum(1, 2)) // 3
}


data class Person(val name: String, val age: Int)

fun shortSyntax() {
    val people = listOf(Person("Alice", 20), Person("Bob", 30))

    // полный синтаксис: лямбда является аргументом функции и у нее явно выписан тип параметра
    println(people.filter({ p: Person -> p.age < 21 }))   // [Person(name=Alice, age=20)]

    // если лямбда - последний аргумент, то можно ее вынести за круглые скобки:
    people.filter() { p: Person -> p.age < 21 }

    // если больше аргументов нет, круглые скобки можно опустить:
    people.filter { p: Person -> p.age < 21 }

    // если компилятор может вывести тип параметра, можно его не писать:
    people.filter { p -> p.age < 21 }

    // если у лямбды есть единственный аргумент, можно обращаться к нему через it:
    people.filter { it.age < 21 }

    // если в теле лямбды просто вызывается метод или возвращается свойство (property),
    // можно вместо лямбды использовать member reference:
    people.map { it.name }
    people.map(Person::name)
}

fun main(args: Array<String>) {
    basicSyntax()
    shortSyntax()
}