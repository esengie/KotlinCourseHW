package game._3bridges

import bloxorz.Direction
import game.TestReadBoard
import org.junit.Assert
import org.junit.Test

class _8TestReadBoardBridges : TestReadBoard() {
    @Test
    fun testClosedBridge() {
        val game = createGameForBridgeLevel("bridgeLevel00.txt")
        Assert.assertEquals(2, game.height)
        Assert.assertEquals(5, game.width)

        // at first bridge is closed
        Assert.assertEquals(null, game.getCellValue(1, 2))
        Assert.assertEquals(null, game.getCellValue(1, 3))

        // switch
        Assert.assertEquals('A', game.getCellValue(2, 3))
    }

    @Test
    fun testOpenedBridge() {
        val game = createGameForBridgeLevel("bridgeLevel00.txt")
        game.processMove(Direction.RIGHT)

        // now bridge is opened
        Assert.assertEquals('a', game.getCellValue(1, 2))
        Assert.assertEquals('a', game.getCellValue(1, 3))

        // block
        Assert.assertEquals('x', game.getCellValue(2, 2))
        Assert.assertEquals('x', game.getCellValue(2, 3))
    }

    @Test
    fun testReadingLightCells() {
        val game = createGameForBridgeLevel("lightCellsLevel1.txt")

        Assert.assertEquals('.', game.getCellValue(2, 1))
    }
}