package _2StateMachine.task2

import _2StateMachine.model.Command
import _2StateMachine.model.Event
import _2StateMachine.model.State
import _2StateMachine.model.StateMachine
import java.util.*

class Builder(start: String) {
    private val eventMap: MutableMap<String, Event> = mutableMapOf()
    private val commandMap: MutableMap<String, Command> = mutableMapOf()
    private val stateMap: MutableMap<String, State> = mutableMapOf()
    val resetEvs: MutableList<Event> = mutableListOf()

    init {
        stateMap[start] = State(start)
    }

    fun event(code: String) {
        eventMap[code] = Event(code)
    }

    fun command(code: String) {
        commandMap[code] = Command(code)
    }

    fun resetEvents(code: String) {
        resetEvs += getEvent(code)
    }

    fun state(code: String, fn: State.() -> Unit) {
        if (!stateMap.contains(code))
            stateMap[code] = State(code)
        getState(code).fn()
    }

    private fun getEvent(code: String) = eventMap[code] ?: throw NoSuchElementException(code)
    private fun getCommand(code: String) = commandMap[code] ?: throw NoSuchElementException(code)
    fun getState(code: String) = stateMap[code] ?: throw NoSuchElementException(code)


    fun State.transition(event: String, target: String) {
        if (!this@Builder.stateMap.containsKey(target))
            this@Builder.stateMap[target] = State(target)

        addTransition(this@Builder.getEvent(event), this@Builder.getState(target))
    }

    fun State.commands(vararg commands: String) {
        commands.forEach { addCommand(this@Builder.getCommand(it)) }
    }
}

fun stateMachine(start: String, fn: Builder.() -> Unit): StateMachine {
    val bd = Builder(start)
    bd.fn()
    val st = StateMachine(bd.getState(start))
    bd.resetEvs.forEach { st.addResetEvent(it) }
    return st
}
