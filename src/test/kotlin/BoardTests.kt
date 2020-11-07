import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class BoardTests {

    @Test
    fun `new board has specified size`() {
        val board = boardOf(w = 4, h = 3)
        assertEquals(4, board.width)
        assertEquals(3, board.height)
    }

    @Test
    fun `new board is empty`() {
        val board = boardOf(w = 4, h = 3)
        (0 until 4).forEach { col ->
            (0 until 3).forEach { row ->
                assertFalse(board.at(row, col))
            }
        }
    }

    @Test
    fun `render board`() {
        val board = boardOf(w = 4, h = 3)
        assertEquals(
            """
                ....
                ....
                ....
            """.trimIndent(),
            board.printed()
        )
    }
}

typealias Board = List<List<Boolean>>

val Board.width: Int get() = this[0].size
val Board.height: Int get() = this.size
fun Board.at(row: Int, col: Int): Boolean = this[row][col]
fun Board.printed(): String =
    (0 until height).joinToString("\n") { row ->
        (0 until width).joinToString("") { col ->
            if (at(row, col)) "*" else "."
        }
    }

fun boardOf(w: Int, h: Int): Board = (0 until h).map { (0 until w).map { false } }


