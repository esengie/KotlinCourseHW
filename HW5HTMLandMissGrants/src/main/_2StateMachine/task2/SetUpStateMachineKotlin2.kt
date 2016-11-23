package _2StateMachine.task2

import _2StateMachine.model.*

// Support the following way to configure the state machine.
// Uncomment the code and make it compile; put all the declarations to the file GarifullinShamilTask2.kt

fun setUpStateMachine(): StateMachine = stateMachine(start = IDLE) {
    event(DOOR_CLOSED)
    event(DRAWER_OPENED)
    event(LIGHT_ON)
    event(DOOR_OPENED)
    event(PANEL_CLOSED)

    command(UNLOCK_PANEL)
    command(LOCK_PANEL)
    command(LOCK_DOOR)
    command(UNLOCK_DOOR)

    state(IDLE) {
        transition(event = DOOR_CLOSED, target = ACTIVE)
        commands(UNLOCK_DOOR, LOCK_PANEL)
    }
    state(ACTIVE) {
        transition(DRAWER_OPENED, WAITING_FOR_LIGHT)
        transition(LIGHT_ON, WAITING_FOR_DRAWER)
    }
    state(WAITING_FOR_LIGHT) {
        transition(LIGHT_ON, UNLOCKED_PANEL)
    }
    state(WAITING_FOR_DRAWER) {
        transition(DRAWER_OPENED, UNLOCKED_PANEL)
    }
    state(UNLOCKED_PANEL) {
        commands(UNLOCK_PANEL, LOCK_DOOR)
        transition(PANEL_CLOSED, IDLE)
    }

    resetEvents(DOOR_OPENED)
}