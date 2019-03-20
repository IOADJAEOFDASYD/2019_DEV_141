package tictactoe.model

/**
 * Describes the state of the game
 */
class Game {
    companion object {
        const val PLAYER_ONE = false // Cross Player
        const val PLAYER_TWO = true // Circle Player
    }

    var currentPlayer : Boolean = PLAYER_ONE
    val board : Board = Board()
}