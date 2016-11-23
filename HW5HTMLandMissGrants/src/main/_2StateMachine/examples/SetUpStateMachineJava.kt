package _2StateMachine.examples

import _2StateMachine.model.*

/*
This version of setting up the state machine is similar to Java.
 */

fun setUpStateMachine(): StateMachine {
    val doorClosed = Event(DOOR_CLOSED)
    val drawerOpened = Event(DRAWER_OPENED)
    val lightOn = Event(LIGHT_ON)
    val doorOpened = Event(DOOR_OPENED)
    val panelClosed = Event(PANEL_CLOSED)

    val unlockPanelCmd = Command(UNLOCK_PANEL)
    val lockPanelCmd = Command(LOCK_PANEL)
    val lockDoorCmd = Command(LOCK_DOOR)
    val unlockDoorCmd = Command(UNLOCK_DOOR)

    val idle = State(IDLE)
    val activeState = State(ACTIVE)
    val waitingForLightState = State(WAITING_FOR_LIGHT)
    val waitingForDrawerState = State(WAITING_FOR_DRAWER)
    val unlockedPanelState = State(UNLOCKED_PANEL)

    val machine = StateMachine(idle)

    idle.addTransition(doorClosed, activeState)
    idle.addCommand(unlockDoorCmd)
    idle.addCommand(lockPanelCmd)

    activeState.addTransition(drawerOpened, waitingForLightState)
    activeState.addTransition(lightOn, waitingForDrawerState)

    waitingForLightState.addTransition(lightOn, unlockedPanelState)
    waitingForDrawerState.addTransition(drawerOpened, unlockedPanelState)

    unlockedPanelState.addCommand(unlockPanelCmd)
    unlockedPanelState.addCommand(lockDoorCmd)
    unlockedPanelState.addTransition(panelClosed, idle)

    machine.addResetEvent(doorOpened)

    return machine
}