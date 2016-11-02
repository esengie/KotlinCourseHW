package _1Dates

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import java.util.*

class TestTask4 {
    @Test fun testIterateOverDateRange() {
        val actualDateRange = ArrayList<MyDate>()
        task4(MyDate(2014, 5, 1), MyDate(2014, 5, 5), {
            date: MyDate -> actualDateRange.add(date)
        })
        val expectedDateRange = arrayListOf(
                MyDate(2014, 5, 1), MyDate(2014, 5, 2), MyDate(2014, 5, 3), MyDate(2014, 5, 4), MyDate(2014, 5, 5))
        assertEquals("Incorrect iteration over five nice spring dates",
                expectedDateRange, actualDateRange)
    }

    @Test fun testIterateOverEmptyRange() {
        var invoked = false
        task4(MyDate(2014, 1, 1), MyDate(2013, 1, 1), { invoked = true })
        assertFalse("Handler was invoked on an empty range", invoked)
    }
}