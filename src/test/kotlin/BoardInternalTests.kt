import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class BoardInternalTests {

    @Test
    fun `liveNeighboursCount 0`() {
        val board = boardOf(
            "...",
            "...",
            "...",
        )
        board.map { row, col ->
            assertEquals(0, board.liveNeighboursCount(row, col))
        }
    }

    @Test
    fun `liveNeighboursCount 1`() {
        val board = boardOf(
            "...",
            ".*.",
            "...",
        )
        board.map { row, col ->
            if (row == 1 && col == 1)
                assertEquals(0, board.liveNeighboursCount(row, col), "at $row $col")
            else
                assertEquals(1, board.liveNeighboursCount(row, col), "at $row $col")
        }
    }
}


