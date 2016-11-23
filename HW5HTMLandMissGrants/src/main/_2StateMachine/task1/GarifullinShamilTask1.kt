package _2StateMachine.task1

import _2StateMachine.model.Command
import _2StateMachine.model.Event
import _2StateMachine.model.State


interface StateBuilder {
    fun commands(vararg commands: Command)
    fun transition(event: Event, state: State)
}

fun State.configure(func: StateBuilder.() -> Unit) {
    val sb = StateBuilderImpl(this)
    sb.func()
}

class StateBuilderImpl(val st: State) : StateBuilder {
    override fun commands(vararg commands: Command) {
        commands.forEach { st.addCommand(it) }
    }

    override fun transition(event: Event, state: State) {
        st.addTransition(event, state)
    }
}

