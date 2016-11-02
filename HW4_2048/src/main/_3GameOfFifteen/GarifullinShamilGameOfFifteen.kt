package task

import board.Cell
import board.Direction
import game.Game

/*
Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
When you finish, you can play the game by executing 'PlayGameOfFifteen' (or choosing the corresponding run configuration).
 */

fun newGameOfFifteen(): Game = Game15()

class Game15 : Game {
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        val lst = (1..15).toMutableList()
        java.util.Collections.shuffle(lst)
        while (!countParity(lst))
            java.util.Collections.shuffle(lst)
        var i = 0
        board.indices.forEach {
            board[it.first, it.second] =
                    if (i < lst.size) lst[i++] else null
        }

    }

    override fun canMove() = true

    override fun hasWon(): Boolean {
        val lst: MutableList<Int> = mutableListOf()
        board.indices.forEach { if (board[it.first, it.second] != null)
            lst.add(board[it.first, it.second]!!) }

        val tmp: List<Int> = lst.toList()
        lst.sort()

        return tmp == lst && board[4, 4] == null
    }

    private fun findNull() : Pair<Int, Int> = board.indices.find { board[it.first, it.second] == null }!!

    override fun processMove(direction: Direction) {
        val (x, y) = findNull()
        val zero = board.getCell(x, y)

        var nonZero : Cell? = null
        board.apply { nonZero = zero.getNeighbour(direction.opposite()) }
        if (nonZero == null) return

        board[zero] = board[nonZero!!]
        board[nonZero!!] = null
    }

    fun Direction.opposite(): Direction = when(this) {
        Direction.LEFT -> Direction.RIGHT
        Direction.RIGHT -> Direction.LEFT
        Direction.UP -> Direction.DOWN
        Direction.DOWN -> Direction.UP
    }

    override fun get(i: Int, j: Int): Int? = board[i, j]
}

