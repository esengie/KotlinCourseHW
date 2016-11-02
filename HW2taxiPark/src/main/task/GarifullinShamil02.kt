package taxiPark.task

import taxiPark.*
import kotlin.collections.*

/*
Если вы не знаете, с какой стороны подступиться к заданию,
можно начать с части про коллекции в kotlin-koans
http://try.kotlinlang.org/#/Kotlin%20Koans/Collections/Introduction/Task.kt.
Там простые задачки на использование конкретных функций.
Их сдавать не надо, там есть ответы.
Домашнее задание по уровню сложности похоже на последние задания в koans,
когда нужно применить несколько функций.
*/

/*
Задание #0.
Строка (из прописных букв английского алфавита) называется красивой, если выполнены ВСЕ следующие условия:
1. она содержит по крайней мере три гласных буквы (гласные буквы: "aeiou")
2. она содержит по крайней мере одну сдвоенную букву (например, ss в строке "klsst")
3. она не содержит сочетания (подстроки) "bu", "ba" и "be" (даже если они являются частью предыдущих условий)
Напишите функцию, которая проверяет, что строка красивая.
Желательно, чтобы функция была небольшого размера и понятная. Четырех строк достаточно :)
В TestNiceStrings есть примеры с комментариями.
 */
fun String.isNice(): Boolean {
    val o = this.count { it in "aeiou" }
    val e = (1 until this.length).any { this[it] == this[it - 1] }
    val a = (setOf("bu", "ba", "be")).count { this.contains(it) }
    return o >= 3 && e && a == 0
}

/*
Для всех остальных заданий используются классы TaxiPark, Driver, Passenger и Order, объявленные в файле TaxiPark.kt.
Цель: понятный код. Можно объявлять столько дополнительных функций, сколько потребуется.
*/

// Задание #1.
// Найти водителей, которые не выполнили ни одного заказа
fun TaxiPark.findFakeDrivers(): Collection<Driver> {
    val trueDrivers: MutableSet<Driver> = mutableSetOf()
    this.orders.forEach { trueDrivers.add(it.driver) }
    return this.allDrivers.filter { it !in trueDrivers }
}

// Задание #2.
// Найти всех клиентов, у которых больше заданного числа поездок
fun TaxiPark.findFaithfulPassengers(minTrips: Int): List<Passenger> {
    val clientToOrder: MutableMap<Passenger, Int> = mutableMapOf()
    this.orders.forEach {
        it.passengers.forEach {
            clientToOrder[it] = (clientToOrder[it] ?: 0) + 1
        }
    }
    return this.allPassengers.filter { clientToOrder[it] ?: 0 > minTrips }

}

// Задание #3.
// Найти всех пассажиров, которых данный водитель возил больше одного раза
fun TaxiPark.findFrequentPassengers(driver: Driver): List<Passenger> {
    val passengerTimes: MutableMap<Passenger, Int> = mutableMapOf()
    this.orders.filter({ it.driver == driver })
            .forEach {
                it.passengers.forEach {
                    passengerTimes[it] = (passengerTimes[it] ?: 0) + 1
                }
            }
    return passengerTimes.filterValues { it > 1 }.keys.toList()
}

// Задание #4.
// Найти пассажиров, которые большую часть поездок осуществили со скидками
fun TaxiPark.findSmartPassengers(): Collection<Passenger> {
    val passengerTimesFull: MutableMap<Passenger, Int> = mutableMapOf()
    val passengerTimesDiscount: MutableMap<Passenger, Int> = mutableMapOf()

    this.orders.forEach { t ->
                t.passengers.forEach {
                    if (t.discount !== null)
                        passengerTimesDiscount[it] = (passengerTimesDiscount[it] ?: 0) + 1
                    else
                        passengerTimesFull[it] = (passengerTimesFull[it] ?: 0) + 1
                }
            }

    return passengerTimesDiscount.filter { (passengerTimesFull[it.key] ?: 0) < it.value }
            .keys.toList()
}

// Задание #5.
// Найти самый частый интервал поездок среди 0-9 минут, 10-19 минут, 20-29 минут и т.д.
// Если нет заказов - вернуть null.
fun TaxiPark.findTheMostFrequentTripDuration(): IntRange? {
    val k : Int = this.orders.groupBy { it.duration.div(10) }
            .maxBy { it.value.size }
            ?.key ?: return null
    return (k * 10 until (k + 1) * 10)
}

// Задание #6.
// Узнать: правда ли, что 20% водителей приносят 80% прибыли?
fun TaxiPark.checkParetoPrinciple(): Boolean {
    val driverToSum : MutableList<Double> = mutableListOf()
    this.orders.groupBy({ it.driver }, { it.cost }).forEach { driverToSum.add(it.value.sum()) }
    return (driverToSum.sortedDescending()
            .subList(0, this.allDrivers.size / 5)
            .sum() / driverToSum.sum()) >= 0.8
}