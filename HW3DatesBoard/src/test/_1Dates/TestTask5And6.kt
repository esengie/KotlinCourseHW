package _1Dates

import _1Dates.TimeInterval.*
import org.junit.Assert.assertEquals
import org.junit.Test

class TestTask5And6 {
    @Test fun testAddTimeIntervals() {
        assertEquals(MyDate(2014, 5, 22), MyDate(1983, 5, 22).addTimeIntervals(YEAR, 31))
        assertEquals(MyDate(1983, 5, 29), MyDate(1983, 5, 22).addTimeIntervals(DAY, 7))
        assertEquals(MyDate(1983, 5, 29), MyDate(1983, 5, 22).addTimeIntervals(WEEK, 1))
    }

    @Test fun testAddOneTimeInterval() {
        assertEquals(MyDate(2015, 5, 8), task5(MyDate(2014, 5, 1)))
    }

    @Test fun testOneMonth() {
        assertEquals(MyDate(2016, 1, 27), task6(MyDate(2014, 1, 1)))
    }

    @Test fun testMonthChange() {
        assertEquals(MyDate(2016, 2, 20), task6(MyDate(2014, 1, 25)))
    }
}
