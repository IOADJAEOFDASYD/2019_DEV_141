package tictactoe.model

/**
 * Describes the state of the game
 */
class Game {
    var currentPlayer : Player = Player.ONE
    val board : Board = Board()

    fun nextPlayer() {
        currentPlayer = when (currentPlayer) {
            Player.ONE -> Player.TWO
            else -> Player.ONE
        }
    }
}