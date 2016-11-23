package game._2solver

import game.TestSolver
import org.junit.Test

class _7TestBasicSolver : TestSolver() {

    @Test
    fun testWhenTwoEqualSolution() = testFindingSolution(
                """
                S * * *
                *     *
                *     *
                * * * T
                """)

    @Test
    fun testSolverCanGoThroughStartCell() = testFindingSolution(
            """
            T
            *
            S * *
            * * *
            * * *
            * * *
            """)

    @Test
    fun testLevel1() = testFindingSolutionForTestLevel("testLevel01.txt")

    @Test
    fun testLevel2() = testFindingSolutionForTestLevel("testLevel02.txt")

    @Test
    fun testLevel3() = testFindingSolutionForTestLevel("testLevel03.txt")

    @Test
    fun testLevel4() = testFindingSolutionForTestLevel("testLevel04.txt")

    @Test
    fun testLevel5() = testFindingSolutionForTestLevel("testLevel05.txt")

    @Test
    fun testLevel6() = testFindingSolutionForTestLevel("testLevel06.txt")

    @Test
    fun testLevel7() = testFindingSolutionForTestLevel("testLevel07.txt")

    @Test
    fun testLevel8() = testFindingSolutionForTestLevel("testLevel08.txt")

    @Test
    fun testLevel9() = testFindingSolutionForTestLevel("testLevel09.txt")

    @Test
    fun testLevel10() = testFindingSolutionForTestLevel("testLevel10.txt")

    @Test
    fun testLevel11() = testFindingSolutionForTestLevel("testLevel11.txt")

    @Test
    fun basicLevel1() = testFindingSolutionForBloxorzLevel("level01.txt")

    @Test
    fun basicLevel3() = testFindingSolutionForBloxorzLevel("level03.txt")

    @Test
    fun basicLevel4() = testFindingSolutionForBloxorzLevel("level04.txt")

    @Test
    fun basicLevel6() = testFindingSolutionForBloxorzLevel("level06.txt")
}