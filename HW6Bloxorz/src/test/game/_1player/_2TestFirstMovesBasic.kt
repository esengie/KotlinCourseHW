package game._1player

import bloxorz.Direction.*
import game.TestFirstMoves
import org.junit.Test

class _2TestFirstMovesBasic : TestFirstMoves() {

    @Test
    fun testMoveRight1() = testMove("S * *", "S x x", RIGHT)

    @Test
    fun testMoveRight2() = testMove("S * * *", "S * * x", RIGHT, RIGHT)

    @Test
    fun testMoveLeft1() = testMove("* * S", "x x S", LEFT)

    @Test
    fun testMoveLeft2() = testMove("* * * S", "x * * S", LEFT, LEFT)

    @Test
    fun testMoveDown1() = testMove(
            """
            S
            *
            *
            """,
            """
            S
            x
            x
            """,
            DOWN)

    @Test
    fun testMoveDown2() = testMove(
            """
            S
            *
            *
            *
            """,
            """
            S
            *
            *
            x
            """,
            DOWN, DOWN)

    @Test
    fun testMoveUp1() = testMove(
            """
            *
            *
            S
            """,
            """
            x
            x
            S
            """,
            UP)

    @Test
    fun testMoveUp2() = testMove(
            """
            *
            *
            *
            S
            """,
            """
            x
            *
            *
            S
            """,
            UP, UP)

    @Test
    fun testRollOverDown() = testMove(
            """
            S * *
              * *
            """,
            """
            S * *
              x x
            """,
            RIGHT, DOWN)

    @Test
    fun testRollOverUp() = testMove(
            """
              * *
            S * *
            """,
            """
              x x
            S * *
            """,
            RIGHT, UP)

    @Test
    fun testRollOverRight1() = testMove(
            """
            S *
            * *
            * *
            """,
            """
            S *
            * x
            * x
            """,
            DOWN, RIGHT)

    @Test
    fun testRollOverRight2() = testMove(
            """
              S
            * *
            * *
            """,
            """
              S
            x *
            x *
            """,
            DOWN, LEFT)

    @Test
    fun testFall1() = testFall("S")

    @Test
    fun testFall2() = testFall(
            """
              *
            * S *
              *
            """)
    @Test
    fun testRollOverAndFall1() = testFall(
            """
              *
            S * *
              *
            """,
            firstMove = RIGHT,
            fallDirections = listOf(UP, DOWN))

    @Test
    fun testRollOverAndFall2() = testFall(
            """
                *
            S * *
                *
            """,
            firstMove = RIGHT,
            fallDirections = listOf(UP, DOWN))

    @Test
    fun testRollOverAndFall3() = testFall(
            """
              S
              *
            * * * """,
            firstMove = DOWN,
            fallDirections = listOf(RIGHT, LEFT))

    @Test
    fun testRollOverAndFall4() = testFall(
            """
              S
            * * *
              *
            """,
            firstMove = DOWN,
            fallDirections = listOf(RIGHT, LEFT))
}