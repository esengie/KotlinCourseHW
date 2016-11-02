package task

// 1. Решение для MyDate. Скопировать ниже содержание файла MyDate.kt после того, как прошли все тесты.

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

// 2. Решение для GameBoard. Скопировать ниже содержание файла BoardImpl.kt после того, как прошли все тесты.

package _2Board

import java.util.*

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)

private fun fill(width: Int): List<List<Cell>> {
    val res = ArrayList<ArrayList<Cell>>()
    for (i in 1..width) {
        res.add(ArrayList<Cell>())
        for (j in 1..width) {
            res[i - 1].add(ConcreteCell(i, j))
        }
    }
    return res
}

class ConcreteCell(override val i: Int, override val j: Int) : Cell

open class SquareBoardImpl(override val width: Int) : SquareBoard {
    private val cells: List<List<Cell>> = fill(width)

    override fun getCellOrNull(i: Int, j: Int): Cell? =
        if (i > width || j > width)
            null else cells[i - 1][j - 1]

    override fun getAllCells(): Collection<Cell> = cells.flatten()

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val ret = ArrayList<Cell>()
        for (j in jRange)
            ret.add(getCell(i, j))
        return ret
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val ret = ArrayList<Cell>()
        for (i in iRange)
            ret.add(getCell(i, j))
        return ret
    }

    override fun Cell.getNeighbour(direction: Direction): Cell =
            when (direction) {
                Direction.UP -> getCell(this.i - 1, this.j)
                Direction.DOWN -> getCell(this.i + 1, this.j)
                Direction.LEFT -> getCell(this.i, this.j - 1)
                Direction.RIGHT -> getCell(this.i, this.j + 1)
            }

    override fun getCell(i: Int, j: Int): Cell = getCellOrNull(i, j) ?: throw IllegalArgumentException()

}

fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl<T>(width)

class GameBoardImpl<T>(width: Int) : SquareBoardImpl(width), GameBoard<T> {
    private val contents: MutableMap<Cell, T?> = HashMap()

    init {
        getAllCells().associateTo(contents, { it -> it to null })
    }

    override fun get(cell: Cell): T = contents[cell] ?: throw IllegalArgumentException()

    override fun set(cell: Cell, value: T?) {
        contents[cell] = value
    }

    override fun get(i: Int, j: Int): T = get(getCell(i, j))

    override fun set(i: Int, j: Int, value: T?) = set(getCell(i, j), value)

    override fun contains(value: T): Boolean = value in contents.values

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> =
            contents.entries.filter { predicate(it.value) }.map { it.key }

    override fun any(predicate: (T?) -> Boolean): Boolean = filter(predicate).any()

    override fun all(predicate: (T?) -> Boolean): Boolean = filter(predicate).count() == contents.count()

}
