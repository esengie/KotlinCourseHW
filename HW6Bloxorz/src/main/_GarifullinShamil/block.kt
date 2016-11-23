package _GarifullinShamil

import bloxorz.Direction

interface Block {
    val standing: Boolean
    fun covers(cell: Cell): Boolean
    fun move(dir: Direction): Block
}
