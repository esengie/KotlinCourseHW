package game._2solver

import game.TestNoSolution
import org.junit.Test

class _6TestBasicNoSolution : TestNoSolution() {

    @Test
    fun testCannotMove() = testNoSolution("S T")

    @Test
    fun testSmallBoard() = testNoSolution("S * T")

    @Test
    fun testExplorePathsOnlyOnce() = testNoSolution("S T * * * * * * * * * * *")

    @Test
    fun testEmptyCells() = testNoSolution(
            """
            S * *
              *
            T * *
            """)
}