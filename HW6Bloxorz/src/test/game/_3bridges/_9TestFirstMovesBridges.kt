package game._3bridges

import bloxorz.Direction.LEFT
import bloxorz.Direction.RIGHT
import game.TestFirstMoves
import bridges.fullBlockBridge
import bridges.simpleBridge
import bridges.twoBridges
import bridges.twoSwitches
import org.junit.Test

class _9TestFirstMovesBridges : TestFirstMoves() {
    @Test
    fun testInitialBridgeState() = testMove(simpleBridge, "S * * a A", "S x x   A", RIGHT)

    @Test
    fun testFallOffTheBridge() = testFall("S * a *", simpleBridge, null, listOf(RIGHT))

    @Test
    fun testClosingBridge() = testMove(simpleBridge, "S * A a *", "S x x a *", RIGHT)

    @Test
    fun testClosingBridgeFullBlock() = testMove(simpleBridge, "S * * A a *", "S * * x a *", RIGHT, RIGHT)

    @Test
    fun testOpeningBridge() = testMove(twoSwitches, "S * A a *", "S x x   *", RIGHT)

    @Test
    fun testFullBlockBridgeHalfPressing() = testMove(fullBlockBridge, "S * A a *", "S x x   *", RIGHT)

    @Test
    fun testFullBlockBridgeFullPressing() = testMove(fullBlockBridge, "S * * A a *", "S * * x a *", RIGHT, RIGHT)

    @Test
    fun testCompoundBridge() = testMove(simpleBridge, "S * A a * a *", "S x x a * a *", RIGHT)

    @Test
    fun testOpeningCompoundBridge() = testMove(twoSwitches, "S * A a * a *", "S x x   *   *", RIGHT)

    @Test
    fun testTwoBridges() = testMove(twoBridges, "S A B a * b *", "S x x a * b *", RIGHT)

    @Test
    fun testTwoBridgesSwitch() = testMove(twoBridges, "S A B a * b *", "S x x   *   *", RIGHT, LEFT, RIGHT)
}
