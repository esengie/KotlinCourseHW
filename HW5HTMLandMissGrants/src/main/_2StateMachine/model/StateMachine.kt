package _2StateMachine.model

data class Command(val code: String)
data class Event(val code: String)

class State(val code: String) {
    private val commands = mutableListOf<Command>()
    private val transitions = mutableMapOf<String, Transition>()

    fun addCommand(command: Command) {
        commands.add(command)
    }

    fun addTransition(event: Event, targetState: State) {
        transitions.put(event.code, Transition(this, targetState, event))
    }

    fun hasTransition(eventCode: String): Boolean = transitions.containsKey(eventCode)

    fun getTargetState(eventCode: String): State = transitions[eventCode]!!.target

    fun processCommand(processor: (String) -> Unit) {
        commands.forEach { processor(it.code) }
    }

    override fun toString() = "State($code)"
}

data class Transition(val source: State, val target: State, val trigger: Event)

class StateMachine(val start: State) {
    private val resetEvents = mutableListOf<Event>()

    fun addResetEvent(event: Event) {
        resetEvents.add(event)
    }

    fun isResetEvent(eventCode: String): Boolean = resetEvents.any { it.code == eventCode }
}

class Controller(val machine: StateMachine, val processor: (String) -> Unit) {
    var state: State = machine.start
        private set

    fun handle(eventCode: String) {
        val target = when {
            state.hasTransition(eventCode) -> state.getTargetState(eventCode)
            machine.isResetEvent(eventCode) -> machine.start
            else -> return // ignore unknown events
        }
        makeTransitionTo(target)
    }

    private fun makeTransitionTo(target: State) {
        state = target
        state.processCommand(processor)
    }
}