package game._3bridges

import game.TestSolver
import org.junit.Ignore
import org.junit.Test

class _13TestBridgeSolver : TestSolver() {

    @Test
    fun testBridgeLevel0() = testFindingSolutionForBridgeLevel("bridgeLevel00.txt")

    @Test
    fun testBridgeLevel1() = testFindingSolutionForBridgeLevel("bridgeLevel01.txt")

    @Test
    fun testBridgeLevel2() = testFindingSolutionForBridgeLevel("bridgeLevel02.txt")

    @Test
    fun testBridgeLevel3() = testFindingSolutionForBridgeLevel("bridgeLevel03.txt")

    @Test
    fun testBridgeLevel4() = testFindingSolutionForBridgeLevel("bridgeLevel04.txt")

    @Test
    fun testBridgeLevel5() = testFindingSolutionForBridgeLevel("bridgeLevel05.txt")

    @Test
    fun testBridgeLevel6() = testFindingSolutionForBridgeLevel("bridgeLevel06.txt")

    @Test
    fun testBridgeLevel7() = testFindingSolutionForBridgeLevel("bridgeLevel07.txt")

    @Test
    fun testBridgeLevel8() = testFindingSolutionForBridgeLevel("bridgeLevel08.txt")

    @Test
    fun testBridgeLevel9() = testFindingSolutionForBridgeLevel("bridgeLevel09.txt")

    @Test
    fun testBridgeLevel10() = testFindingSolutionForBridgeLevel("bridgeLevel10.txt")

    @Test
    fun testBridgeLevel11() = testFindingSolutionForBridgeLevel("bridgeLevel11.txt")

    @Test
    fun basicLevel1() = testFindingSolutionForBloxorzLevel("level02.txt")

    @Test
    fun basicLevel3() = testFindingSolutionForBloxorzLevel("level05.txt")

    @Test
    fun basicLevel4() = testFindingSolutionForBloxorzLevel("level07.txt")

    @Test
    fun lightLevel1() = testFindingSolutionForBridgeLevel("lightCellsLevel1.txt")

    @Test
    fun lightLevel2() = testFindingSolutionForBridgeLevel("lightCellsLevel2.txt")

    @Test
    fun lightLevel3() = testFindingSolutionForBridgeLevel("lightCellsLevel3.txt")

    @Test
    fun lightLevel4() = testFindingSolutionForBridgeLevel("lightCellsLevel4.txt")

    @Test
    fun lightLevel5() = testFindingSolutionForBridgeLevel("lightCellsLevel5.txt")
}