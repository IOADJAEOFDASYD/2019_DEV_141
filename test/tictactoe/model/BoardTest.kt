package tictactoe.model

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BoardTest {

    @Test
    fun testInitialization() {
        val board = Board()

        for (x in 0..2)
            for (y in 0..2)
                assertEquals(null, board.getSquare(x, y))
    }

    @Test
    fun testOutOfBoundsCoordinates() {
        val board = Board()

        assertFailsWith(IllegalArgumentException::class) {
            board.getSquare(3, 0)
        }

        assertFailsWith(IllegalArgumentException::class) {
            board.getSquare(0, 3)
        }

        assertFailsWith(IllegalArgumentException::class) {
            board.getSquare(-1, 0)
        }

        assertFailsWith(IllegalArgumentException::class) {
            board.getSquare(0, -1)
        }

        assertFailsWith(IllegalArgumentException::class) {
            board.getSquare(0, -1)
        }

        assertFailsWith(IllegalArgumentException::class) {
            board.setSquare(3, 0, Player.ONE)
        }

        assertFailsWith(IllegalArgumentException::class) {
            board.setSquare(0, 3, Player.ONE)
        }

        assertFailsWith(IllegalArgumentException::class) {
            board.setSquare(-1, 0, Player.ONE)
        }

        assertFailsWith(IllegalArgumentException::class) {
            board.setSquare(0, -1, Player.ONE)
        }

        assertFailsWith(IllegalArgumentException::class) {
            board.setSquare(0, -1, Player.ONE)
        }
    }

    @Test
    fun testIsFilled() {
        val board = Board()

        assertFalse(board.isFilled())

        for (x in 0..2) {
            for (y in 0..1) {
                board.setSquare(x, y, Player.ONE)

                assertFalse(board.isFilled())
            }
        }

        for (x in 0..2)
            board.setSquare(x, 2, Player.ONE)

        assertTrue(board.isFilled())
    }

    @Test
    fun testIsRowFilledAndMatch() {
        val board = Board()

        for (y in 0..2) {
            assertFalse(board.isRowFilled(y))
            assertFalse(board.isRowMatch(y))
        }

        board.setSquare(0, 0, Player.ONE)

        for (y in 0..2) {
            assertFalse(board.isRowFilled(y))
            assertFalse(board.isRowMatch(y))
        }

        board.setSquare(1, 0, Player.ONE)

        for (y in 0..2) {
            assertFalse(board.isRowFilled(y))
            assertFalse(board.isRowMatch(y))
        }

        board.setSquare(2, 0, Player.ONE)

        assertTrue(board.isRowFilled(0))
        assertTrue(board.isRowMatch(0))
        for (y in 1..2) {
            assertFalse(board.isRowFilled(y))
            assertFalse(board.isRowMatch(y))
        }
    }

    @Test
    fun testIsColumnFilledAndMatch() {
        val board = Board()

        for (x in 0..2) {
            assertFalse(board.isColumnFilled(x))
            assertFalse(board.isColumnMatch(x))
        }

        board.setSquare(0, 0, Player.ONE)

        for (x in 0..2) {
            assertFalse(board.isColumnFilled(x))
            assertFalse(board.isColumnMatch(x))
        }

        board.setSquare(0, 1, Player.ONE)

        for (x in 0..2) {
            assertFalse(board.isColumnFilled(x))
            assertFalse(board.isColumnMatch(x))
        }

        board.setSquare(0, 2, Player.TWO)

        assertTrue(board.isColumnFilled(0))
        assertFalse(board.isColumnMatch(0))
        for (x in 1..2) {
            assertFalse(board.isColumnFilled(x))
            assertFalse(board.isColumnMatch(x))
        }

        board.setSquare(0, 2, Player.ONE)

        assertTrue(board.isColumnMatch(0))
    }

    @Test
    fun testIsDiagonalFilledAndMatch() {
        val board = Board()

        assertFalse(board.isDiagonalFilled(true))
        assertFalse(board.isDiagonalMatch(true))
        
        assertFalse(board.isDiagonalFilled(false))
        assertFalse(board.isDiagonalMatch(false))

        board.setSquare(0, 0, Player.ONE)

        assertFalse(board.isDiagonalFilled(true))
        assertFalse(board.isDiagonalMatch(true))
        
        assertFalse(board.isDiagonalFilled(false))
        assertFalse(board.isDiagonalMatch(false))

        board.setSquare(1, 1, Player.ONE)

        assertFalse(board.isDiagonalFilled(true))
        assertFalse(board.isDiagonalMatch(true))
        
        assertFalse(board.isDiagonalFilled(false))
        assertFalse(board.isDiagonalMatch(false))

        board.setSquare(2, 2, Player.TWO)

        assertTrue(board.isDiagonalFilled(true))
        assertFalse(board.isDiagonalMatch(true))
        
        assertFalse(board.isDiagonalFilled(false))
        assertFalse(board.isDiagonalMatch(false))

        board.setSquare(0, 2, Player.ONE)

        assertTrue(board.isDiagonalFilled(true))
        assertFalse(board.isDiagonalMatch(true))
        
        assertFalse(board.isDiagonalFilled(false))
        assertFalse(board.isDiagonalMatch(false))

        board.setSquare(2, 0, Player.TWO)

        assertTrue(board.isDiagonalFilled(true))
        assertFalse(board.isDiagonalMatch(true))
        
        assertTrue(board.isDiagonalFilled(false))
        assertFalse(board.isDiagonalMatch(false))
        
        board.setSquare(2, 2, Player.ONE)

        assertTrue(board.isDiagonalMatch(true))
        assertFalse(board.isDiagonalMatch(false))
        
        board.setSquare(2, 0, Player.ONE)
        
        assertTrue(board.isDiagonalMatch(true))
        assertTrue(board.isDiagonalMatch(false))
    }

    @Test
    fun testWinner() {
        val board = Board()

        assertEquals(null, board.getWinner())

        for (y in 0..2)
            board.setSquare(0, y, Player.ONE)

        assertEquals(Player.ONE, board.getWinner())

        for (y in 0..2)
            board.setSquare(0, y, Player.TWO)

        assertEquals(Player.TWO, board.getWinner())
    }
}