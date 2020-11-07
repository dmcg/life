import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class Tests {

    @Test
    fun `new board has specified size`() {
        val board = boardOf(w = 2, h = 3)
        assertEquals(3, board.height)
        assertEquals(2, board.width)
    }

    @Test
    fun `new board is empty`() {
        val board = boardOf(w = 2, h = 3)
        (0 until 2).forEach { col ->
            (0 until 3).forEach { row ->
                assertFalse(board.at(row, col))
            }
        }
    }
}

typealias Board = List<List<Boolean>>
val Board.width: Int get() = this[0].size
val Board.height: Int get() = this.size
fun Board.at(row: Int, col: Int): Boolean = this[row][col]

fun boardOf(w: Int, h: Int): Board = (0 until h).map { (0 until w).map { false } }


