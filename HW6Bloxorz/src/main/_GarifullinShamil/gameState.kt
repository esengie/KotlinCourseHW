package _GarifullinShamil

import bloxorz.Game
import bridges.Bridge
import bridges.BridgeState
import java.util.*

data class GameState(val block: Block, val bridges: MutableMap<Bridge, BridgeState>) {
    override fun hashCode(): Int {
        return block.hashCode() * 31 + bridges.hashCode()
    }

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as GameState

        if (block != other.block) return false
        if (bridges != other.bridges) return false

        return true
    }

    fun copy(): GameState = GameState(block, HashMap(bridges))
}