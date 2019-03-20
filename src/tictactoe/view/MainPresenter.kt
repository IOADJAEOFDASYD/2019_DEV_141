package tictactoe.view

import tictactoe.model.Game

class MainPresenter : MainUseCase.Presenter {

    private lateinit var mView : MainUseCase.View
    private var mGame : Game? = null

    override fun attachView(view: MainUseCase.View) {
        mView = view
    }

    override fun startGame() {
        mGame = Game()

        mView.drawGame(mGame!!)
    }

    override fun play(x: Int, y: Int) {
        mGame?.let { game ->
            // If the game isn't over && square is empty
            if (game.board.getWinner() == null && game.board.getSquare(x, y) == null) {
                game.board.setSquare(x, y, game.currentPlayer)

                // Change current player
                game.nextPlayer()
            }

            mView.drawGame(game)
        } ?: run {
            mView.handleError("Game not started")
        }
    }
}