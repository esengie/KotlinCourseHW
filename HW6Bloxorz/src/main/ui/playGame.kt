package ui

import _GarifullinShamil.createGame
import bridges.BridgesInfo
import bridges.bridgeInfoForLevels
import java.io.File


fun defaultFilePath(): String {
    val fileName = "level01.txt"
    val path = "levels/bloxorz/"
//    val fileName = "bridgeLevel08.txt"
//    val fileName = "lightCellsLevel1.txt"
//    val path = "levels/bridges/"
    return "$path$fileName"
}

fun boardAndBridgesInfo(args: Array<String>): Pair<String, BridgesInfo?> {
    val filePath = args.firstOrNull() ?: defaultFilePath()

    val file = File(filePath)
    val bridgesInfo = bridgeInfoForLevels[filePath.substringAfterLast("/")]

    val board = file.readText()
    return board to bridgesInfo
}

fun main(args: Array<String>) {

    val (board, bridgesInfo) = boardAndBridgesInfo(args)

    val game = createGame(board, bridgesInfo)

    playGame(game, BloxorzGameSettings)
}