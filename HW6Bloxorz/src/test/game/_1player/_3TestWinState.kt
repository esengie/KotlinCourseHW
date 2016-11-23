package game._1player

import game.TestGame
import bloxorz.Direction.LEFT
import bloxorz.Direction.RIGHT
import org.junit.Assert
import org.junit.Test

class _3TestWinState : TestGame() {
    @Test
    fun testFirstCellBlockOnTarget() {
        val game = createGame("S * * * T *")
        game.processMove(RIGHT)
        game.processMove(RIGHT)

        Assert.assertFalse("Block should be in vertical state", game.hasWon())
    }

    @Test
    fun testSecondCellBlockOnTarget() {
        val game = createGame("S * T *")
        game.processMove(RIGHT)

        Assert.assertFalse("Block should be in vertical state", game.hasWon())
    }

    @Test
    fun testActualWin() {
        val game = createGame("""T * * S""")
        game.processMove(LEFT)
        game.processMove(LEFT)

        Assert.assertTrue(game.hasWon())
    }
}