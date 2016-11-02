package task

import board.Cell
import board.Direction
import board.GameBoard
import board.SquareBoard
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
    override val indices: List<Pair<Int, Int>>
        get() {
            val res: MutableList<Pair<Int, Int>> = mutableListOf()
            for (i in 1 .. width) {
                for (j in 1 .. width) {
                    res.add(Pair(i, j))
                }
            }
            return res
        }
    private val cells: List<List<Cell>> = fill(width)

    override fun getCellOrNull(i: Int, j: Int): Cell? =
            if (i > width || j > width || i <= 0 || j <= 0)
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

    override fun Cell.getNeighbour(direction: Direction): Cell? =
            when (direction) {
                Direction.UP -> getCellOrNull(this.i - 1, this.j)
                Direction.DOWN -> getCellOrNull(this.i + 1, this.j)
                Direction.LEFT -> getCellOrNull(this.i, this.j - 1)
                Direction.RIGHT -> getCellOrNull(this.i, this.j + 1)
            }

    override fun getCell(i: Int, j: Int): Cell = getCellOrNull(i, j) ?: throw IllegalArgumentException()

}

fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl<T>(width)

class GameBoardImpl<T>(width: Int) : SquareBoardImpl(width), GameBoard<T> {

    private val contents: MutableMap<Cell, T?> = HashMap()

    init {
        getAllCells().associateTo(contents, { it -> it to null })
    }

    override fun get(cell: Cell): T? = contents[cell]

    override fun set(cell: Cell, value: T?) {
        contents[cell] = value
    }

    override fun get(i: Int, j: Int): T? = get(getCell(i, j))

    override fun set(i: Int, j: Int, value: T?) = set(getCell(i, j), value)

    override fun contains(value: T): Boolean = value in contents.values

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> =
            contents.entries.filter { predicate(it.value) }.map { it.key }

    override fun find(predicate: (T?) -> Boolean): Cell? =
            (filter(predicate)).toMutableList().getOrElse(0, { it -> null })


    override fun any(predicate: (T?) -> Boolean): Boolean = filter(predicate).any()

    override fun all(predicate: (T?) -> Boolean): Boolean = filter(predicate).count() == contents.count()
}
