package _GarifullinShamil

import bloxorz.Direction
import bloxorz.Game
import bridges.*
import java.util.*

// Your solution should live in this folder/package only (rename _SurnameName accordingly.)
// You may add as many subpackages as you need, but the function 'createGame' below should live in the root _SurnameName package.
// Please DON'T copy the interface 'Game' here.

fun createGame(board: String, bridgesInfo: BridgesInfo? = null): Game = GameImpl(board, bridgesInfo)

class GameImpl(board: String, bridgesInfo: BridgesInfo?) : Game {
    override fun processMove(direction: Direction) {
        state = GameState(state.block.move(direction), state.bridges)
    }

    override fun hasWon(): Boolean = state.block.standing && state.block.covers(winPos)

    private fun getCell(i: Int, j: Int): Cell? = positions[i]?.get(j)
    override fun getCellValue(i: Int, j: Int): Char? {
        val cell = getCell(i - 1, j - 1) ?: return null
        if (state.block.covers(cell))
            return 'x'
        if (cell.type == CellType.BRIDGE) {
            if (getBridgeState(cell.c) == BridgeState.CLOSED) {
                return null
            }
        }
        return cell.c
    }

    private fun getBridgeState(c: Char): BridgeState {
        val b: Bridge = _bridgesInfo.bridges[c]!!
        if (state.bridges.containsKey(b)) {
            return state.bridges[b]!!
        }
        return b.initialState
    }

    override fun toString(): String =
            (0 until height).map { i ->
                (0 until width).map { j ->
                    getCellValue(i + 1, j + 1) ?: " "
                }.joinToString(" ")
            }.joinToString("\n", transform = String::trimEnd)

    private val directions: List<Direction> = listOf(Direction.UP,
            Direction.DOWN,
            Direction.LEFT,
            Direction.RIGHT)

    private fun Direction.reverse(): Direction = when (this) {
        Direction.UP -> Direction.DOWN
        Direction.DOWN -> Direction.UP
        Direction.RIGHT -> Direction.LEFT
        Direction.LEFT -> Direction.RIGHT
    }

    override fun suggestMoves(): List<Direction>? {
        val saved: GameState = state.copy()
        val q: Queue<GameState> = LinkedList()
        q.add(state)
        val prevs: MutableMap<GameState, Pair<Direction, GameState>> = mutableMapOf()
        val seen: MutableSet<GameState> = mutableSetOf()
        while (!q.isEmpty()) {
            val st: GameState = q.poll()
            seen.add(st)
            for (i in directions) {
                state = st.copy()
                processMove(i)
                if (state != initState && !seen.contains(state)) {
                    q.add(state)
                    prevs[state] = i to st
                }
                if (hasWon()) {
                    val res: MutableList<Direction> = mutableListOf()
                    while (state.block != saved.block) {
                        res.add(prevs[state]!!.first)
                        state = prevs[state]!!.second
                    }
                    return res.reversed()
                }
            }
        }
        state = saved
        return null
    }

    private val positions: Map<Int, Map<Int, Cell>>

    private val _bridgesInfo: BridgesInfo
    private val initState: GameState
    private val winPos: Cell
    private var state: GameState

    private val _height: Int
    private val _width: Int

    override val height: Int
        get() = _height
    override val width: Int
        get() = _width

    init {
        val _positions = mutableMapOf<Int, MutableMap<Int, Cell>>()
        val boardArr = board.split('\n')
                .map { x -> x.trimEnd().toCharArray().filterIndexed { i, x -> i % 2 == 0 } }

        var initPos: Cell? = null
        var finPos: Cell? = null
        boardArr.indices.forEach { i ->
            run {
                val nestedMap = mutableMapOf<Int, Cell>()
                boardArr[i].indices.forEach { j ->
                    run {
                        val ch = boardArr[i][j]
                        if (ch != ' ') {
                            nestedMap[j] = Cell(i, j, ch)
                        }
                        if (ch == 'S') {
                            initPos = Cell(i, j, ch)
                        }
                        if (ch == 'T') {
                            finPos = Cell(i, j, ch)
                        }
                    }
                }
                _positions[i] = nestedMap
            }
        }

        positions = _positions
        initState = GameState(Standing(initPos ?:
                throw IllegalStateException("Game board is illegal")), mutableMapOf())
        winPos = finPos ?: Cell(-1, -1, 'T')
        state = initState

        _height = boardArr.size
        _width = boardArr[0].size
        _bridgesInfo = bridgesInfo ?: BridgesInfo(mapOf(), mapOf())
    }

    private fun Cell.moved(dir: Direction): Cell? = when (dir) {
        Direction.UP -> getCell(i - 1, j)
        Direction.DOWN -> getCell(i + 1, j)
        Direction.RIGHT -> getCell(i, j + 1)
        Direction.LEFT -> getCell(i, j - 1)
    }

    private inner class Standing(val pos: Cell) : Block {
        override val standing: Boolean = true

        override fun covers(cell: Cell): Boolean = cell == pos

        override fun move(dir: Direction): Block {
            val pos1: Cell = pos.moved(dir) ?: return resetState()
            val pos2: Cell = pos1.moved(dir) ?: return resetState()
            return Laying((pos1.handleCases(true) ?: return resetState()) to
                    (pos2.handleCases(true) ?: return resetState()), dir)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other?.javaClass != javaClass) return false

            other as Standing

            if (pos != other.pos) return false
            if (standing != other.standing) return false

            return true
        }

        override fun hashCode(): Int {
            var result = pos.hashCode()
            result = 31 * result + standing.hashCode()
            return result
        }
    }

    fun Direction.parallel(dir: Direction): Boolean = when (dir) {
        Direction.UP -> this == Direction.UP || this == Direction.DOWN
        Direction.DOWN -> this == Direction.UP || this == Direction.DOWN
        Direction.RIGHT -> this == Direction.RIGHT || this == Direction.LEFT
        Direction.LEFT -> this == Direction.RIGHT || this == Direction.LEFT
    }

    private fun Cell.handleCases(light: Boolean): Cell? {
        when (this.type) {
            CellType.CELL -> return this
            CellType.LIGHT -> {
                if (light) {
                    return this
                }
                return null
            }
            CellType.SWITCH -> {
                handleSwitch(c, light)
                return this
            }
            CellType.BRIDGE -> {
                val b: Bridge = _bridgesInfo.bridges[c] ?:
                        throw IllegalStateException("No bridge in bridges!")
                var bState: BridgeState = b.initialState
                if (state.bridges.containsKey(b)) {
                    bState = state.bridges[b]!!
                }
                if (bState == BridgeState.OPENED) {
                    return this
                }
                resetState()
                return null
            }
        }
    }

    private fun resetState(): Block {
        state = initState
        return initState.block
    }

    private fun handleSwitch(c: Char, light: Boolean) {
        val sw: Switch = _bridgesInfo.switches[c] ?:
                throw IllegalStateException("No such switch " + c)
        if (sw.type == SwitchType.FULL_BLOCK && light) {
            return
        }
        state = GameState(state.block, HashMap(state.bridges))
        when (sw.action) {
            SwitchAction.OPEN -> state.bridges[sw.bridge] = BridgeState.OPENED
            SwitchAction.CLOSE -> state.bridges[sw.bridge] = BridgeState.CLOSED
            SwitchAction.CHANGE -> state.bridges[sw.bridge] = getBridgeState(sw.bridge.name).flip()
        }
    }

    private fun BridgeState.flip(): BridgeState = when (this) {
        BridgeState.OPENED -> BridgeState.CLOSED
        BridgeState.CLOSED -> BridgeState.OPENED
    }


    private inner class Laying(val pos: Pair<Cell, Cell>, val myDir: Direction) : Block {
        override val standing: Boolean = false

        override fun covers(cell: Cell): Boolean = cell == pos.first || cell == pos.second

        override fun move(dir: Direction): Block {
            if (myDir.parallel(dir)) {
                var go: Cell = pos.second.moved(dir) ?: return resetState()
                if (go == pos.first) {
                    go = pos.first.moved(dir) ?: return resetState()
                }
                return Standing(go.handleCases(false) ?: return resetState())
            }
            val pos1: Cell = pos.first.moved(dir) ?: return resetState()
            val pos2: Cell = pos.second.moved(dir) ?: return resetState()

            return Laying((pos1.handleCases(true) ?: return resetState()) to
                    (pos2.handleCases(true) ?: return resetState()), myDir)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other?.javaClass != javaClass) return false

            other as Laying

            if (pos != other.pos && pos.second to pos.first != other.pos) return false
            if (standing != other.standing) return false

            return true
        }

        override fun hashCode(): Int {
            var result = pos.first.hashCode() + pos.second.hashCode()
            result = 31 * result + standing.hashCode()
            return result
        }
    }
}
