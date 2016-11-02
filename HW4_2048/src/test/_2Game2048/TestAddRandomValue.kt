package tests

import org.junit.Assert
import org.junit.Test
import task.addRandomValue

class TestAddRandomValue : TestGameWithSmallNumbers() {
    @Test
    fun test1() = testAddingOneNumber("0000 0000 0200 0000")

    @Test
    fun test2() = testAddingOneNumber("2222 0000 0000 0000")

    @Test
    fun test3() = testAddingOneNumber("2000 4000 0200 0008")

    @Test
    fun test4() = testAddingOneNumber("0248 2020 0208 4442")

    fun testAddingOneNumber(input: String) {
        val board = createBoard(input)
        board.addRandomValue()
        val result = board.print()
        Assert.assertEquals("Only one element should be different.\nInput $input.\nResult $result",
                1, input.indices.count { input[it] != result[it] })
        val index = input.indices.find { input[it] != result[it] }!!
        Assert.assertTrue("New element should be 2 or 4", result[index] in setOf('2', '4'))
    }
}