package bridges

import bridges.BridgeState.CLOSED

data class BridgesInfo(val bridges: Map<Char, Bridge>, val switches: Map<Char, Switch>)

data class Bridge(val name: Char, val initialState: BridgeState = CLOSED)
data class Switch(val name: Char, val bridge: Bridge, val action: SwitchAction, val type: SwitchType)

enum class BridgeState {
    OPENED, // can step on it
    CLOSED  // equivalent to empty cell
}
enum class SwitchAction { OPEN, CLOSE, CHANGE }
enum class SwitchType { FULL_BLOCK, HALF_BLOCK }