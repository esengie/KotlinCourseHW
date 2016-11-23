package game._1player

import bloxorz.Direction.*
import game.TestPlayer
import org.junit.Test


class _4TestBasicPlayer : TestPlayer() {

    @Test
    fun testLevel1() = testSolutionForBasicLevel("testLevel01.txt", listOf(LEFT, DOWN, LEFT))

    @Test
    fun testLevel2() = testSolutionForBasicLevel("testLevel02.txt", listOf(LEFT, UP, LEFT, DOWN, LEFT, UP))

    @Test
    fun testLevel3() = testSolutionForBasicLevel("testLevel03.txt", listOf(RIGHT, RIGHT, RIGHT, DOWN, RIGHT, DOWN, LEFT, UP, LEFT, LEFT))

    @Test
    fun testLevel4() = testSolutionForBasicLevel("testLevel04.txt", listOf(RIGHT, UP, LEFT, LEFT, LEFT, DOWN, LEFT, UP, RIGHT, DOWN, LEFT))

    @Test
    fun testLevel5() = testSolutionForBasicLevel("testLevel05.txt", listOf(UP, LEFT, LEFT, UP, LEFT, DOWN, LEFT, DOWN, RIGHT, DOWN, RIGHT, RIGHT))

    @Test
    fun testLevel6() = testSolutionForBasicLevel("testLevel06.txt", listOf(DOWN, LEFT, DOWN, LEFT, UP, RIGHT))

    @Test
    fun testLevel7() = testSolutionForBasicLevel("testLevel07.txt", listOf(UP, RIGHT, DOWN, DOWN, RIGHT, RIGHT, UP, RIGHT, DOWN, LEFT))

    @Test
    fun testLevel8() = testSolutionForBasicLevel("testLevel08.txt", listOf(DOWN, DOWN, RIGHT, RIGHT, UP, RIGHT, UP, RIGHT, DOWN, DOWN, LEFT, UP, RIGHT, UP))

    @Test
    fun testLevel9() = testSolutionForBasicLevel("testLevel09.txt", listOf(UP, UP, RIGHT, DOWN, RIGHT, UP, LEFT, DOWN))

    @Test
    fun testLevel10() = testSolutionForBasicLevel("testLevel10.txt", listOf(LEFT, DOWN, LEFT, UP, RIGHT, RIGHT, DOWN))

    @Test
    fun testLevel11() = testSolutionForBasicLevel("testLevel11.txt", listOf(UP, RIGHT, UP, RIGHT, RIGHT, DOWN, LEFT, UP))
}