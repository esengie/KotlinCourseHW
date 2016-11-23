package ui

import bloxorz.Direction.*
import bloxorz.Game
import java.awt.*
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

class GameVisualizer(val game: Game, val settings: GameSettings) : JPanel() {
    init {
        isFocusable = true
        addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                if (game.hasWon() == false) {
                    val direction = when (e.keyCode) {
                        KeyEvent.VK_LEFT -> LEFT
                        KeyEvent.VK_RIGHT -> RIGHT
                        KeyEvent.VK_DOWN -> DOWN
                        KeyEvent.VK_UP -> UP
                        KeyEvent.VK_ENTER -> game.suggestMoves()?.first()
                        else -> null
                    }
                    if (direction != null) {
                        game.processMove(direction)
                    }
                }
                repaint()
            }
        })
    }

    override fun paint(g: Graphics) {
        super.paint(g)
        g.color = settings.backgroundColor
        g.fillRect(0, 0, this.size.width, this.size.height)
        for (x in 1..game.height) {
            for (y in 1..game.width) {
                val value = game.getCellValue(x, y)
                drawTile(g as Graphics2D, value, y - 1, x - 1)
            }
        }
    }

    private fun offsetCoors(arg: Int): Int {
        return arg * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN
    }

    private fun drawTile(g: Graphics2D, value: Char?, x: Int, y: Int) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE)

        val xOffset = offsetCoors(x)
        val yOffset = offsetCoors(y)
        g.color = settings.getColor(value)
        g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 14, 14)
        g.color = settings.getForegroundColor()
        val font = Font(FONT_NAME, Font.BOLD, FONT_SIZE)
        g.font = font

        val s = value.toString()
        val fm = getFontMetrics(font)

        val w = fm.stringWidth(s)
        val h = -fm.getLineMetrics(s, g).baselineOffsets[2].toInt()

        if (value != null && settings.drawValue(value))
            g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2)

        if (game.hasWon()) {
            g.color = Color(255, 255, 255, 30)
            g.fillRect(0, 0, width, height)
            g.color = Color(78, 139, 202)
            g.font = Font(FONT_NAME, Font.BOLD, 48)
            if (game.hasWon()) {
                g.drawString("Congratulations!", 68, 150)
            }
        }
        g.font = Font(FONT_NAME, Font.PLAIN, 18)
    }
}

private fun computeWindowSize(game: Game): Pair<Int, Int> {
    val (h, w) = with (game) { height to width }
    val windowHeight = h * TILE_SIZE + h * TILES_MARGIN + 30
    val windowWidth = w * TILE_SIZE + w * TILES_MARGIN + 5

    return Pair(
            Math.min(windowHeight, MAX_WINDOW_SIZE),
            Math.min(windowWidth, MAX_WINDOW_SIZE))
}

private val FONT_NAME = "Arial"
private val FONT_SIZE = 16
private val TILE_SIZE = 48
private val TILES_MARGIN = 1
private val MAX_WINDOW_SIZE = 800

abstract class GameSettings(val name: String, val backgroundColor: Color) {
    abstract fun getForegroundColor(): Color
    abstract fun getColor(value: Char?): Color
    abstract fun drawValue(label: Char?): Boolean
}

object BloxorzGameSettings : GameSettings("Bloxorz Game", Color(0x787878)) {
    override fun getColor(value: Char?) = when (value) {
        'x' -> Color(0xFFA07A)
        'T' -> Color(0x555555)
        '.' -> Color(0xC8C8C8)
        null -> backgroundColor
        else -> Color(0xA9A9A9)
    }

    override fun getForegroundColor(): Color {
        return Color.WHITE
    }

    override fun drawValue(label: Char?): Boolean {
        return label != 'x' && label != '*' && label != '.' && label != 'S'
    }
}

fun playGame(game: Game, settings: GameSettings) {
    with (JFrame()) {
        title = settings.name
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        val (h, w) = computeWindowSize(game)
        setSize(w, h)
        isResizable = true

        add(GameVisualizer(game, settings))

        setLocationRelativeTo(null)
        isVisible = true
    }
}