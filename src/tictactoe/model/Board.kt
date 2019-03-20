package tictactoe.model

/**
 * Describes the current state of the board
 */
class Board {
    companion object {
        const val BLOCK_EMPTY = 0
        const val BLOCK_PLAYER_ONE = 1
        const val BLOCK_PLAYER_TWO = 2
    }

    val blocks = IntArray(3 * 3) { BLOCK_EMPTY }

}