package _2Board

interface Cell {
    val i: Int
    val j: Int
}

enum class Direction {
    UP, DOWN, RIGHT, LEFT
}

interface SquareBoard {
    val width: Int

    fun getCell(i: Int, j: Int): Cell
    fun getCellOrNull(i: Int, j: Int): Cell?

    fun getAllCells(): Collection<Cell>

    fun getRow(i: Int, jRange: IntProgression): List<Cell>
    fun getColumn(iRange: IntProgression, j: Int): List<Cell>

    fun Cell.getNeighbour(direction: Direction): Cell?
}

interface GameBoard<T> : SquareBoard {

    operator fun get(cell: Cell): T?
    operator fun set(cell: Cell, value: T?)

    operator fun get(i: Int, j: Int): T?
    operator fun set(i: Int, j: Int, value: T?)

    operator fun contains(value: T): Boolean

    fun filter(predicate: (T?) -> Boolean): Collection<Cell>
    fun any(predicate: (T?) -> Boolean): Boolean
    fun all(predicate: (T?) -> Boolean): Boolean
}