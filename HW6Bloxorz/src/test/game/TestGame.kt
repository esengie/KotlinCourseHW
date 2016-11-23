package game

import bridges.BridgesInfo
import bloxorz.Direction
import bloxorz.Game
import bridges.bridgeInfoForLevels
import org.junit.Assert
import java.io.File

private val basicLevelDir = "levels/basic"
private val bridgeLevelDir = "levels/bridges"
private val bloxorzLevelDir = "levels/bloxorz"

abstract class TestGame {
    fun createGame(board: String, bridgesInfo: BridgesInfo? = null): Game =
            _GarifullinShamil.createGame(board, bridgesInfo)

    fun createGameForFile(filePath: String, bridgesInfo: BridgesInfo? = null): Game =
            createGame(File(filePath).readText(), bridgesInfo)
}

abstract class TestReadBoard: TestGame() {
    fun createGameForBasicLevel(fileName: String) = createGameForFile("$basicLevelDir/$fileName")
    fun createGameForBridgeLevel(fileName: String) = createGameForFile("$bridgeLevelDir/$fileName", bridgeInfoForLevels[fileName])
}

abstract class TestPlayer: TestGame() {

    fun testSolutionForBasicLevel(fileName: String, moves: List<Direction>) =
            testSolution("$basicLevelDir/$fileName", null, moves)

    fun testSolutionForBridgeLevel(fileName: String, moves: List<Direction>) =
            testSolution("$bridgeLevelDir/$fileName", bridgeInfoForLevels[fileName], moves)

    fun testSolution(filePath: String, bridgesInfo: BridgesInfo?, moves: List<Direction>) {
        val game = createGameForFile(filePath, bridgesInfo)

        for (move in moves) {
            game.processMove(move)
        }
        Assert.assertTrue("The directions should won the game, but got to the following state:\n$game", game.hasWon())
    }
}

abstract class TestSolver: TestGame() {
    fun testFindingSolution(input: String, bridgesInfo: BridgesInfo? = null) {
        val game = createGame(input, bridgesInfo)
        val path = game.suggestMoves()
                ?: throw AssertionError("No solution found for\n$input")
        println(path)
        for (direction in path) {
            game.processMove(direction)
        }
        Assert.assertTrue("Found solution should be correct.\nBoard: $input\nSolution: $path", game.hasWon())
    }

    fun testFindingSolutionForTestLevel(fileName: String) = testFindingSolution(File("$basicLevelDir/$fileName").readText())

    fun testFindingSolutionForBloxorzLevel(fileName: String) =
            testFindingSolution(File("$bloxorzLevelDir/$fileName").readText(), bridgeInfoForLevels[fileName])

    fun testFindingSolutionForBridgeLevel(fileName: String) =
            testFindingSolution(File("$bridgeLevelDir/$fileName").readText(), bridgeInfoForLevels[fileName])

    fun testFoundSolution(expected: List<Direction>, input: String) {
        val path = createGame(input.trimIndent()).suggestMoves()
        Assert.assertEquals("Found wrong solution", expected, path)
    }
}

abstract class TestNoSolution: TestGame() {
    fun testNoSolution(input: String, bridgesInfo: BridgesInfo? = null) {
        val path = createGame(input.trimIndent(), bridgesInfo).suggestMoves()
        Assert.assertNull("No solution expected for $input", path)
    }
}

abstract class TestFirstMoves: TestGame() {

    fun testMove(input: String, result: String, vararg directions: Direction) {
        testMove(null, input, result, *directions)
    }

    fun testMove(bridgesInfo: BridgesInfo?, input: String, result: String, vararg directions: Direction) {
        val game = createGame(input.trimIndent(), bridgesInfo)
        for (direction in directions) {
            game.processMove(direction)
        }
        Assert.assertEquals("The block should move as specified", result.trimIndent().normalizeEndings(), game.toString().normalizeEndings())
    }

    fun testFall(input: String, firstMove: Direction? = null, fallDirections: List<Direction> = Direction.values().toList()) {
        testFall(input, null, firstMove, fallDirections)
    }

    fun testFall(input: String, bridgesInfo: BridgesInfo?, firstMove: Direction? = null, directions: List<Direction> = Direction.values().toList()) {
        val game = createGame(input.trimIndent(), bridgesInfo)
        val initialState = game.toString()
        for (direction in directions) {
            // game is going to be reset after each fall, so make the first move each time
            if (firstMove != null) game.processMove(firstMove)
            game.processMove(direction)
            Assert.assertEquals("The block should fall from the board and return to its start position", initialState.normalizeEndings(), game.toString().normalizeEndings())
        }
    }

    private fun String.normalizeEndings() = lines().joinToString("\n")
}