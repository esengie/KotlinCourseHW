package _2StateMachine

class TestSample : AbstractTestStateMachine() {
    override fun setUpStateMachine() =
            _2StateMachine.examples.setUpStateMachine()
}

class TestTask1 : AbstractTestStateMachine() {
    override fun setUpStateMachine() =
            _2StateMachine.task1.setUpStateMachine()
}

class TestTask2 : AbstractTestStateMachine() {
    override fun setUpStateMachine() =
            _2StateMachine.task2.setUpStateMachine()
}