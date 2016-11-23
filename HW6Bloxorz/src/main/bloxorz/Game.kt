package bloxorz

enum class Direction {
    UP, DOWN, RIGHT, LEFT;
}

interface Game {

    val height: Int
    val width: Int

    // x - block; * - regular cell; . - light cell; S - start; T - target;
    // A..Z - switches; a..z - open bridges, null - closed bridges; null - no cell
    fun getCellValue(i: Int, j: Int): Char?

    fun processMove(direction: Direction)
    fun hasWon(): Boolean

    // should return the current State of the board;
    // the format is the same as used in tests except for 'x' (block) and ' ' (closed bridges)
    override fun toString(): String

    // null - if there's no solution
    fun suggestMoves(): List<Direction>?
}