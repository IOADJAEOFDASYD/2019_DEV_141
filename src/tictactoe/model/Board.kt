package tictactoe.model

/**
 * Describes the current state of the board
 */
class Board {
    private val squares = arrayOfNulls<Player>(3 * 3)

    fun getSquare(x: Int, y: Int) : Player? = squares[x + y * 3]

    fun setSquare(x: Int, y: Int, player: Player?) {
        squares[x + y * 3] = player
    }

    /**
     * Returns 'true' if the board is fully filled, 'false' otherwise.
     */
    fun isFilled() : Boolean {
        for (square in squares)
            if (square == null)
                return false

        return true
    }

    /**
     * Returns 'true' if the specified row is fully filled by players, 'false' otherwise.
     */
    fun isRowFilled(y: Int) : Boolean {
        for (x in 0..2)
            if (getSquare(x, y) == null)
                return false

        return true
    }

    /**
     * Returns 'true' if the specified column is fully filled by players, 'false' otherwise.
     */
    fun isColumnFilled(x: Int) : Boolean {
        for (y in 0..2)
            if (getSquare(x, y) == null)
                return false

        return true
    }

    /**
     * Returns 'true' if the specified diagonal is fully filled by players, 'false' otherwise.
     * 'topLeft' as 'true' -> topLeft to bottomRight diagonal.
     * 'topLeft' as 'false' -> bottomLeft to topRight diagonal.
     */
    fun isDiagonalFilled(topLeft: Boolean) : Boolean {
        return if (topLeft)
            getSquare(0, 0) != null && getSquare(1, 1) != null && getSquare(2, 2) != null
        else
            getSquare(0, 2) != null && getSquare(1, 1) != null && getSquare(2, 0) != null
    }

    /**
     * Returns 'true' if the specified row is fully filled by the same player, 'false' otherwise.
     */
    fun isRowMatch(y: Int) : Boolean = isRowFilled(y) && getSquare(0, y) == getSquare(1, y) && getSquare(1, y) == getSquare(2, y)

    /**
     * Returns 'true' if the specified column is fully filled by the same player, 'false' otherwise.
     */
    fun isColumnMatch(x: Int) : Boolean = isColumnFilled(x) && getSquare(x, 0) == getSquare(x, 1) && getSquare(x, 1) == getSquare(x, 2)

    /**
     * Returns 'true' if the specified diagonal is fully filled by the same player, 'false' otherwise.
     * 'topLeft' as 'true' -> topLeft to bottomRight diagonal.
     * 'topLeft' as 'false' -> bottomLeft to topRight diagonal.
     */
    fun isDiagonalMatch(topLeft: Boolean) : Boolean {
        if (!isDiagonalFilled(topLeft))
            return false

        return if (topLeft)
            getSquare(0, 0) == getSquare(1, 1) && getSquare(1, 1) == getSquare(2, 2)
        else
            getSquare(0, 2) == getSquare(1, 1) && getSquare(1, 1) == getSquare(2, 0)
    }

    /**
     * Returns the game winner 'Player' if a player has won, 'null' otherwise.
     */
    fun getWinner() : Player? {
        // Check if lines match
        for (y in 0..2)
            if (isRowMatch(y))
                return getSquare(0, y)

        // Check if columns match
        for (x in 0..2)
            if (isColumnMatch(x))
                return getSquare(x, 0)

        // Check topLeft-bottomRight diagonal
        if (isDiagonalMatch(true))
            return getSquare(0, 0)

        // Check bottomLeft-topRight diagonal
        if (isDiagonalMatch(false))
            return getSquare(0, 2)

        // No winner
        return null
    }
}