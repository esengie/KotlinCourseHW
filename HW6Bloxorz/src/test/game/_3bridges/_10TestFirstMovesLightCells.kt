package game._3bridges

import game.TestFirstMoves
import bloxorz.Direction.*
import org.junit.Test

class _10TestFirstMovesLightCells : TestFirstMoves() {
    @Test
    fun testMove1() = testMove("S * .", "S x x", RIGHT)

    @Test
    fun testMove2() = testMove("S . *", "S x x", RIGHT)

    @Test
    fun testMove3() = testMove("S . .", "S x x", RIGHT)

    @Test
    fun testFall() = testFall(
            """
              .
            S * * .
                .
            """,
            firstMove = RIGHT,
            fallDirections = listOf(UP, DOWN, LEFT))
}
