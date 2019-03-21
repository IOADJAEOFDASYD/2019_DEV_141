package tictactoe.model

import org.junit.Test
import kotlin.test.assertEquals

class GameTest {

    @Test
    fun testNextPlayer() {
        val game = Game()

        assertEquals(Player.ONE, game.currentPlayer)

        game.nextPlayer()

        assertEquals(Player.TWO, game.currentPlayer)

        game.nextPlayer()

        assertEquals(Player.ONE, game.currentPlayer)
    }
}