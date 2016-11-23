package game._3bridges

import bloxorz.Direction.*
import game.TestPlayer
import org.junit.Test


class _11TestBridgePlayer : TestPlayer() {
    @Test
    fun testBridgeLevel0() = testSolutionForBridgeLevel("bridgeLevel00.txt", listOf(RIGHT, UP, RIGHT))

    @Test
    fun testBridgeLevel1() = testSolutionForBridgeLevel("bridgeLevel01.txt", listOf(RIGHT, RIGHT, LEFT, LEFT, RIGHT, UP, RIGHT))

    @Test
    fun testBridgeLevel2() = testSolutionForBridgeLevel("bridgeLevel02.txt", listOf(RIGHT, RIGHT, LEFT, LEFT, DOWN, DOWN, RIGHT, RIGHT))

    @Test
    fun testBridgeLevel3() = testSolutionForBridgeLevel("bridgeLevel03.txt", listOf(RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT))

    @Test
    fun testBridgeLevel4() = testSolutionForBridgeLevel("bridgeLevel04.txt", listOf(DOWN, RIGHT, RIGHT, DOWN, RIGHT, DOWN, RIGHT, RIGHT, RIGHT, UP, UP, RIGHT, UP, LEFT, DOWN, RIGHT, UP, RIGHT, RIGHT))

    @Test
    fun testBridgeLevel8() = testSolutionForBridgeLevel("bridgeLevel08.txt", listOf(RIGHT, RIGHT, DOWN, UP, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, DOWN, RIGHT, DOWN, DOWN))

    @Test
    fun testLightCellsLevel1() = testSolutionForBridgeLevel("lightCellsLevel1.txt", listOf(DOWN, RIGHT, RIGHT, DOWN, RIGHT, DOWN, RIGHT))
}