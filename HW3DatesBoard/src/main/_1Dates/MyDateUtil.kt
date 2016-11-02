package _1Dates

import _1Dates.TimeInterval.*
import java.time.LocalDate

fun MyDate.nextDay() = addTimeIntervals(DAY, 1)

fun MyDate.addTimeIntervals(timeInterval: TimeInterval, number: Int): MyDate {
    val localDate = LocalDate.of(year, month, dayOfMonth)
    val newDate = when (timeInterval) {
        DAY -> localDate.plusDays(number.toLong())
        WEEK -> localDate.plusWeeks(number.toLong())
        YEAR -> localDate.plusYears(number.toLong())
    }
    return MyDate(newDate.year, newDate.monthValue, newDate.dayOfMonth)
}