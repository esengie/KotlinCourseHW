package game._3bridges

import game.TestNoSolution
import bridges.fullBlockBridge
import bridges.twoSwitches
import org.junit.Test

class _12TestBridgesNoSolution : TestNoSolution() {
    @Test
    fun testFullBlockBridge() = testNoSolution("S A * * * a T", fullBlockBridge)

    @Test
    fun testOpenedBridge() = testNoSolution("S A * * * a T", twoSwitches)
}