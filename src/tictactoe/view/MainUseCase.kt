package tictactoe.view

import tictactoe.model.Game

object MainUseCase {

    interface View {
        fun onAttach()
        fun drawGame(game: Game)
        fun handleError(error: String)
    }

    interface Presenter {
        fun attachView(view : View)
        fun startGame()
        fun play(x: Int, y: Int)
    }
}