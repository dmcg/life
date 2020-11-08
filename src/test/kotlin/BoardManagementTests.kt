import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class BoardManagementTests {

    @Test
    fun `new board has specified size`() {
        val board = boardOf(width = 4, height = 3)
        assertEquals(4, board.width)
        assertEquals(3, board.height)
    }

    @Test
    fun `new board is empty`() {
        val board = boardOf(width = 4, height = 3)
        board.map { row, col ->
            assertFalse(board.isAliveAt(row, col))
        }
    }

    @Test
    fun `render board`() {
        val board = boardOf(width = 4, height = 3)
        assertEquals(
            """
                ....
                ....
                ....
            """.trimIndent(),
            board.printed()
        )
    }

    @Test
    fun `parse board`() {
        val boardAsString = """
            ...*
            ...*
            ....
            """.trimIndent()
        assertEquals(boardAsString, boardOf(boardAsString).printed())
    }
}


