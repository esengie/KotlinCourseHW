package task

import board.Cell
import board.Direction
import board.GameBoard
import game.Game
import java.util.*

/*
Your task is to implement the game 2048 https://en.wikipedia.org/wiki/2048_(video_game)
Implement the helper function first (moveAndMergeEqual in GarifullinShamilGame2048Helper.kt), then extension functions below.

Try to use methods of SquareBoard and GameBoard instead of reimplementing them.
(You may use and add extensions like SquareBoard.indices() as well).

When you finish, you can play the game by executing 'PlayGame2048' (or choosing the corresponding run configuration).
 */
fun newGame2048(): Game = Game2048()

class Game2048 : Game {
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        board.addRandomValue()
        board.addRandomValue()
    }

    override fun canMove() = board.any { it == null }

    override fun hasWon() = board.any { it == 2048 }

    override fun processMove(direction: Direction) {
        if (board.moveValues(direction)) {
            board.addRandomValue()
        }
    }

    override fun get(i: Int, j: Int): Int? = board[i, j]
}

val random = Random()
fun generateRandomStartValue() = if (random.nextInt(10) == 9) 4 else 2

/*
Add a random value to a free cell in a board.
The value should be 2 for 90% cases, 4 for the rest of the cases.
Use the generateRandomStartValue function above.
Examples and tests in TestAddRandomValue.
 */
fun GameBoard<Int?>.addRandomValue() {
    val cells: List<Cell> = this.filter { it == null }.toList()
    val nth = random.nextInt(cells.size)
    this[cells[nth]] = generateRandomStartValue()
}

/*
Move values in a specified rowOrColumn only.
Use the helper function 'moveAndMergeEqual' (in GarifullinShamilGame2048Helper.kt).
The values should be moved to the beginning of the row (or column), in the same manner as in the function 'moveAndMergeEqual'.
Examples and tests in TestMoveValuesInRowOrColumn.
 */
fun GameBoard<Int?>.moveValuesInRowOrColumn(rowOrColumn: List<Cell>): Boolean {
    val lst: List<Int> = rowOrColumn.map { this[it] }.moveAndMergeEqual { it -> it * 2 }
    var i = 0
    for (it in rowOrColumn) {
        if (i < lst.size)
            this[it] = lst[i]
        else
            this[it] = null
        ++i
    }
    return lst.size < rowOrColumn.size && lst.size > 0
}

/*
Move values by the rules of the 2048 game to the specified direction.
Use the moveValuesInRowOrColumn function above.
Examples and tests in TestMoveValues.
 */
fun GameBoard<Int?>.moveValues(direction: Direction): Boolean = when (direction) {
    Direction.UP, Direction.DOWN  -> {
        var dir: IntProgression = 1..width
        if (direction == Direction.DOWN)
            dir = dir.reversed()
        var b = false
        for (i in 1..width) {
            val c =  this.moveValuesInRowOrColumn(this.getColumn(dir, i))
            b = b || c
        }
        b
    }
    Direction.LEFT, Direction.RIGHT -> {
        var dir: IntProgression = 1..width
        if (direction == Direction.RIGHT)
            dir = dir.reversed()
        var b = false
        for (i in 1 .. width) {
            val c =  this.moveValuesInRowOrColumn(this.getRow(i, dir))
            b = b || c
        }
        b
    }
}