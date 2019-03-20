package tictactoe.view

import tictactoe.model.Board
import tictactoe.model.Game

class MainPresenter : MainUseCase.Presenter {

    companion object {
        const val WINNER_NONE = 0
        const val WINNER_PLAYER_ONE = 1
        const val WINNER_PLAYER_TWO = 2
    }

    private lateinit var mView : MainUseCase.View
    private var mGame : Game? = null

    override fun attachView(view: MainUseCase.View) {
        mView = view
    }

    override fun startGame() {
        mGame = Game()

        // Update view with initial game state
        mView.drawGame(mGame!!)
    }

    override fun play(x: Int, y: Int) {
        mGame?.let { game ->
            if (isSquareEmpty(x, y)) {
                game.board.blocks[x + y * 3] = when (game.currentPlayer) {
                    Game.PLAYER_ONE -> Board.BLOCK_PLAYER_ONE
                    else -> Board.BLOCK_PLAYER_TWO
                }

                // Change current player
                game.currentPlayer = !game.currentPlayer

                // Check if game is over
                val isGameOver = isGameOver()
                if (isGameOver != WINNER_NONE)
                    mView.gameOver(isGameOver)
                else
                    mView.drawGame(game)
            }
        } ?: run {
            TODO("error handling when no game started")
        }
    }

    private fun isSquareEmpty(x: Int, y: Int) : Boolean {
        mGame?.let {
            return it.board.blocks[x + y * 3] == Board.BLOCK_EMPTY
        } ?: return false
    }

    private fun isGameOver() : Int {
        mGame?.let {
            // Check if lines match
            for (y in 0..2) {
                if (isRowFilled(y) && it.board.blocks[0 + y * 3] == it.board.blocks[1 + y * 3]
                    && it.board.blocks[1 + y * 3]  == it.board.blocks[2 + y * 3]) {
                    return when (it.board.blocks[0 + y * 3]) {
                        Board.BLOCK_PLAYER_ONE -> WINNER_PLAYER_ONE
                        else -> WINNER_PLAYER_TWO
                    }
                }
            }

            // Check if columns match
            for (x in 0..2) {
                if (isColumnFilled(x) && it.board.blocks[x + 0 * 3] == it.board.blocks[x + 1 * 3]
                    && it.board.blocks[x + 1 * 3]  == it.board.blocks[x + 2 * 3]) {
                    return when (it.board.blocks[x + 0 * 3]) {
                        Board.BLOCK_PLAYER_ONE -> WINNER_PLAYER_ONE
                        else -> WINNER_PLAYER_TWO
                    }
                }
            }

            // Check left-right diagonal
            if (it.board.blocks[0] != Board.BLOCK_EMPTY && it.board.blocks[0] == it.board.blocks[1 + 1 * 3]
                && it.board.blocks[1 + 1 * 3] == it.board.blocks[2 + 2 * 3]) {
                return when (it.board.blocks[0]) {
                    Board.BLOCK_PLAYER_ONE -> WINNER_PLAYER_ONE
                    else -> WINNER_PLAYER_TWO
                }
            }

            // Check right-left diagonal
            if (it.board.blocks[2] != Board.BLOCK_EMPTY && it.board.blocks[2] == it.board.blocks[1 + 1 * 3]
                && it.board.blocks[1 + 1 * 3] == it.board.blocks[0 + 2 * 3]) {
                return when (it.board.blocks[2]) {
                    Board.BLOCK_PLAYER_ONE -> WINNER_PLAYER_ONE
                    else -> WINNER_PLAYER_TWO
                }
            }

            // No winner
            return WINNER_NONE
        } ?: return WINNER_NONE
    }

    private fun isRowFilled(y: Int) : Boolean {
        mGame?.let {
            for (x in 0..2)
                if (it.board.blocks[x + y * 3] == Board.BLOCK_EMPTY)
                    return false

            return true
        } ?: return false
    }

    private fun isColumnFilled(x: Int) : Boolean {
        mGame?.let {
            for (y in 0..2)
                if (it.board.blocks[x + y * 3] == Board.BLOCK_EMPTY)
                    return false

            return true
        } ?: return false
    }
}