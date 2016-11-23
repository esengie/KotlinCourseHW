package _2StateMachine

import _2StateMachine.model.*
import org.junit.Assert
import org.junit.Test

abstract class AbstractTestStateMachine {
    abstract fun setUpStateMachine(): StateMachine

    @Test
    fun testOneTransition() {
        testStateMachine(
                listOf(DOOR_CLOSED),
                listOf(),
                ACTIVE
        )
    }

    @Test
    fun testIncorrectEvents() {
        testStateMachine(
                listOf(DOOR_CLOSED, DRAWER_OPENED, DOOR_CLOSED, PANEL_CLOSED),
                listOf(),
                WAITING_FOR_LIGHT
        )
    }

    @Test
    fun testUnlockedPanel() {
        testStateMachine(
                listOf(DOOR_CLOSED, LIGHT_ON, DRAWER_OPENED),
                listOf(UNLOCK_PANEL, LOCK_DOOR),
                UNLOCKED_PANEL
        )
    }

    @Test
    fun testFullCycle() {
        testStateMachine(
                listOf(DOOR_CLOSED, LIGHT_ON, DRAWER_OPENED, PANEL_CLOSED),
                listOf(UNLOCK_PANEL, LOCK_DOOR, UNLOCK_DOOR, LOCK_PANEL),
                IDLE
        )
    }

    @Test
    fun testResetEvents() {
        testStateMachine(
                listOf(DOOR_CLOSED, DOOR_OPENED),
                listOf(UNLOCK_DOOR, LOCK_PANEL),
                IDLE
        )
    }

    @Test
    fun testResetEventsWithCommands() {
        testStateMachine(
                listOf(DOOR_CLOSED, LIGHT_ON, DOOR_OPENED),
                listOf(UNLOCK_DOOR, LOCK_PANEL),
                IDLE
        )
    }

    fun testStateMachine(events: List<String>, commands: List<String>, finalState: String) {
        val actualCommands = mutableListOf<String>()
        val controller = Controller(setUpStateMachine(), { actualCommands.add(it) })
        for (code in events) {
            controller.handle(code)
        }
        println(actualCommands)
        println(controller.state)
        Assert.assertEquals(finalState, controller.state.code)
        Assert.assertEquals(commands, actualCommands);
    }
}