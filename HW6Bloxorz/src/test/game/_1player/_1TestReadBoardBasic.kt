package game._1player

import bloxorz.Direction
import game.TestReadBoard
import org.junit.Assert
import org.junit.Test

class _1TestReadBoardBasic : TestReadBoard() {
    @Test
    fun testReadLevel1() {
        val game = createGameForBasicLevel("testLevel01.txt")
        Assert.assertEquals(2, game.height)
        Assert.assertEquals(5, game.width)

        // block stays at start position
        Assert.assertEquals('x', game.getCellValue(1, 4))

        // target cell
        Assert.assertEquals('T', game.getCellValue(2, 1))

        // regular cell
        Assert.assertEquals('*', game.getCellValue(1, 2))

        // no cell
        Assert.assertEquals(null, game.getCellValue(1, 1))
    }

    @Test
    fun testGameStateAfterOneMove() {
        val game = createGameForBasicLevel("testLevel01.txt")
        game.processMove(Direction.LEFT)

        // now start position is visible
        Assert.assertEquals('S', game.getCellValue(1, 4))

        // block
        Assert.assertEquals('x', game.getCellValue(1, 2))
        Assert.assertEquals('x', game.getCellValue(1, 3))
    }

    @Test
    fun testGameStateAfterOneFall() {
        val game = createGameForBasicLevel("testLevel01.txt")
        game.processMove(Direction.UP)

        // block again stays at start position
        Assert.assertEquals('x', game.getCellValue(1, 4))
    }
}