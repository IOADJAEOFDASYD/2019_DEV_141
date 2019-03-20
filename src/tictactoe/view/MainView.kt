package tictactoe.view

import tictactoe.model.Board
import tictactoe.model.Game
import java.awt.Dimension
import java.awt.Rectangle
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel

class MainView : MainUseCase.View {

    companion object {
        const val UI_OFFSET = 50 // Initial/final distance from buttons to frame
        const val UI_BUTTON_SIZE = 50 // Width/Height of a button
        const val UI_BUTTON_DISTANCE = 25 // Distance between buttons
    }

    private val presenter : MainUseCase.Presenter = MainPresenter()

    /** UI Components **/
    private var buttonBlocks = arrayOfNulls<JButton>(3 * 3)
    private var labelPlayer : JLabel? = null

    override fun drawGame(game: Game) {
        // Update current player
        labelPlayer?.text = if (game.currentPlayer == Game.PLAYER_ONE) "Player One (X)" else "Player Two (O)"

        // Update the board
        for (y in 0..2) {
            for (x in 0..2) {
                val idx = x + y * 3

                buttonBlocks[idx]?.text = when (game.board.blocks[idx]) {
                    Board.BLOCK_PLAYER_ONE -> "X"
                    Board.BLOCK_PLAYER_TWO -> "O"
                    else -> " "
                }
            }
        }
    }

    override fun gameOver(winner: Int) {
        labelPlayer?.text = "Game Over"
    }

    override fun onAttach() {
        presenter.attachView(this)

        setupView()

        // Start the game
        presenter.startGame()
    }

    private fun setupView() {
        val frame = JFrame()

        // Coordinates for the buttons
        var buttonX = UI_OFFSET
        var buttonY = UI_OFFSET

        // Add the buttons for the game squares
        for (y in 0..2) {
            for (x in 0..2) {
                val buttonBlock = JButton("?")
                buttonBlock.bounds = Rectangle(buttonX, buttonY, UI_BUTTON_SIZE, UI_BUTTON_SIZE)
                buttonBlock.isFocusPainted = false

                // Click action for the button
                buttonBlock.addActionListener {
                    presenter.play(x, y)
                    println("pressed $x / $y")
                }

                buttonBlocks[x + y * 3] = buttonBlock
                frame.add(buttonBlock)

                buttonX += UI_BUTTON_SIZE + UI_BUTTON_DISTANCE
            }

            // Next line of buttons
            buttonX = UI_OFFSET
            buttonY += UI_BUTTON_SIZE + UI_BUTTON_DISTANCE
        }

        // Add the label for current player
        labelPlayer = JLabel("Current Player")
        labelPlayer?.bounds = Rectangle(25, 0, 200, 25)
        frame.add(labelPlayer)

        val windowSize = UI_OFFSET * 2 + UI_BUTTON_SIZE * 3 + UI_BUTTON_DISTANCE * 2

        // Configure the window frame
        frame.size = Dimension(windowSize, windowSize)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.layout = null
        frame.isResizable = false
        frame.isVisible = true

    }
}