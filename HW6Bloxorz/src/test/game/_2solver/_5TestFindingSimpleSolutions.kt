package game._2solver

import game.TestSolver
import bloxorz.Direction.*
import org.junit.Test

class _5TestFindingSimpleSolutions : TestSolver() {

    @Test
    fun testBasicSolutionInTheRight() = testFoundSolution(listOf(RIGHT, RIGHT), "S * * T")

    @Test
    fun testBasicSolutionInTheLeft() = testFoundSolution(listOf(LEFT, LEFT), "T * * S")

    @Test
    fun testBasicSolutionInTheTop() = testFoundSolution(listOf(UP, UP),
            """
            T
            *
            *
            S
            """)

    @Test
    fun testBasicSolutionInTheBottom() = testFoundSolution(listOf(DOWN, DOWN),
            """
            S
            *
            *
            T
            """)

    @Test
    fun testSimpleCase() = testFoundSolution(listOf(DOWN, DOWN, RIGHT, RIGHT),
            """
            S * *
            *     *
            *     *
            * * * T
            """)
}