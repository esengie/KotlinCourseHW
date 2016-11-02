package _1Dates

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        if (year < other.year)
            return -1
        if (year > other.year)
            return 1
        if (month < other.month)
            return -1
        if (month > other.month)
            return 1
        if (dayOfMonth < other.dayOfMonth)
            return -1
        if (dayOfMonth > other.dayOfMonth)
            return 1
        return 0
    }

    infix operator fun plus(t: TimeInterval): MyDate {
        return this.addTimeIntervals(t, 1)
    }

    infix operator fun plus(t: Pair<TimeInterval, Int>): MyDate {
        return this.addTimeIntervals(t.first, t.second)
    }
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate> {
    operator fun iterator(): Iterator<MyDate> {
        return DateRangeInterator(start, endInclusive)
    }

}

class DateRangeInterator(first: MyDate, last: MyDate) : Iterator<MyDate> {
    private var next = first
    private val finalElement = last
    private var hasNext: Boolean = next <= finalElement

    override fun next(): MyDate {
        val value = next
        if (value == finalElement) {
            hasNext = false
        } else {
            next = next.addTimeIntervals(TimeInterval.DAY, 1)
        }
        return value
    }

    override fun hasNext(): Boolean = hasNext

}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;
}

infix operator fun TimeInterval.times(i: Int): Pair<TimeInterval, Int> {
    return Pair(this, i)
}