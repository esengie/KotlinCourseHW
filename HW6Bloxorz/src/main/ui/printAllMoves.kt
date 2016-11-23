package ui

import _GarifullinShamil.createGame


fun main(args: Array<String>) {
    val (board, bridgesInfo) = boardAndBridgesInfo(args)
    val game = createGame(board, bridgesInfo)
    println(game)
    println()

    val path = game.suggestMoves()

    if (path == null) {
        println("There is no solution for this board")
        return
    }
    println(path)
    println()

    for (direction in path) {
        game.processMove(direction)
        println(game)
        println("-------------------")
    }
}