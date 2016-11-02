package _1Dates

import _1Dates.TimeInterval.*

fun task1(date1: MyDate, date2: MyDate): Boolean {
    return date1 < date2
}

fun task2(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in DateRange(first, last)
}

fun task3(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in first..last
}

fun task4(firstDate: MyDate, secondDate: MyDate, handler: (MyDate) -> Unit) {
    for (date in firstDate..secondDate) {
        handler(date)
    }
}

fun task5(today: MyDate): MyDate {
    return today + YEAR + WEEK
}

fun task6(today: MyDate): MyDate {
    return today + YEAR * 2 + WEEK * 3 + DAY * 5
}
