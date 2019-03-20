package tictactoe.view

import tictactoe.model.Game

object MainUseCase {

    interface View {
        fun onAttach()
        fun drawGame(game: Game) // Draws the current game state
        fun gameOver(winner: Int)
    }

    interface Presenter {
        fun attachView(view : View)
        fun startGame() // Starts a new game
        fun play(x: Int, y: Int) // Current player plays on (x, y) square
    }
}