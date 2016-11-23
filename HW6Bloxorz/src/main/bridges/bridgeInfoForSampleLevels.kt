package bridges

import _GarifullinShamil.*
import bridges.BridgeState.OPENED
import bridges.SwitchAction.OPEN
import bridges.SwitchAction.CLOSE
import bridges.SwitchType.FULL_BLOCK
import bridges.SwitchType.HALF_BLOCK

val simpleBridge = bridgesInfo {
    bridge('a') {
        switch('A', type = HALF_BLOCK)
    }
}

val fullBlockBridge = bridgesInfo {
    bridge('a') {
        switch('A', type = FULL_BLOCK)
    }
}

val twoBridges = bridgesInfo {
    bridge('a') {
        switch('A')
    }
    bridge('b') {
        switch('B')
    }
}

val twoSwitches = bridgesInfo {
    bridge('a', OPENED) {
        switch('A', CLOSE)
        switch('B', OPEN)
    }
}

val threeBridges = bridgesInfo {
    bridge('a') {
        switch('A')
    }
    bridge('b') {
        switch('B', type = FULL_BLOCK)
    }
    bridge('c') {
        switch('C', type = FULL_BLOCK)
    }
}

val bridgesInfo02 = bridgesInfo {
    bridge('a') {
        switch('A', type = HALF_BLOCK)
    }
    bridge('b') {
        switch('B', type = FULL_BLOCK)
    }
}

val bridgesInfo05 = bridgesInfo {
    bridge('a', OPENED) {
        switch('A')
    }
    bridge('b', OPENED)
    bridge('c', OPENED) {
        switch('B', CLOSE)
        switch('C', OPEN)
        switch('D')
    }
}

val bridgesInfo07 = fullBlockBridge

val bridgeInfoForLevels = mapOf(
        "bridgeLevel00.txt" to simpleBridge,
        "bridgeLevel01.txt" to twoBridges,
        "bridgeLevel02.txt" to fullBlockBridge,
        "bridgeLevel03.txt" to twoSwitches,
        "bridgeLevel04.txt" to simpleBridge,
        "bridgeLevel05.txt" to fullBlockBridge,
        "bridgeLevel06.txt" to twoBridges,
        "bridgeLevel07.txt" to twoSwitches,
        "bridgeLevel08.txt" to twoSwitches,
        "bridgeLevel09.txt" to twoBridges,
        "bridgeLevel10.txt" to twoBridges,
        "bridgeLevel11.txt" to threeBridges,
        "level02.txt" to bridgesInfo02,
        "level05.txt" to bridgesInfo05,
        "level07.txt" to bridgesInfo07,
        "lightCellsLevel3.txt" to fullBlockBridge,
        "lightCellsLevel4.txt" to simpleBridge,
        "lightCellsLevel5.txt" to twoBridges
)