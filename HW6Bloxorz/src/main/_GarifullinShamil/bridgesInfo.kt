package _GarifullinShamil

import bridges.*
import bridges.BridgeState.CLOSED
import bridges.SwitchAction.CHANGE
import bridges.SwitchType.HALF_BLOCK

// Your solution should live in this folder/package only (rename _SurnameName accordingly.)
// You may add as many subpackages as you need, but the function 'bridgesInfo' below should live in the root _SurnameName package.
// Please DON'T copy the class 'BridgesInfo' and others here.

fun bridgesInfo(init: BuilderOfAll.() -> Unit): BridgesInfo {
    val bridges = mutableMapOf<Char, Bridge>()
    val switches = mutableMapOf<Char, Switch>()
    BuilderOfAll(bridges, switches).init()
    return BridgesInfo(bridges, switches)
}

class BuilderOfAll(val bridges: MutableMap<Char, Bridge>, val switches: MutableMap<Char, Switch>)
class Builder(val bridge: Bridge, val switches: MutableMap<Char, Switch>)

// These functions need receivers to works correctly. The declarations below are only used to have the compiled code.

fun BuilderOfAll.bridge(name: Char, initialState: BridgeState = CLOSED, init: Builder.() -> Unit = {}) {
    val bridge = Bridge(name, initialState)
    Builder(bridge, switches).init()
    bridges[name] = bridge
}

fun Builder.switch(name: Char, action: SwitchAction = CHANGE, type: SwitchType = HALF_BLOCK) {
    switches[name] = Switch(name, bridge, action, type)
}