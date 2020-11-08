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
        board.forEach { row, col ->
            assertFalse(board.at(row, col))
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

    @Test
    fun `list neighbours`() {
        val board = boardOf(3, 3)
        assertEquals(
            listOf(2 to 2, 2 to 0, 2 to 1, 0 to 2, 0 to 1, 1 to 2, 1 to 0, 1 to 1),
            board.listNeighbours(0, 0)
        )
        // TODO - doesn't work on boards smaller than 3 x 3
    }

    @Test
    fun `liveNeigboursCount 0`() {
        val board = listOf(
            listOf(false, false, false),
            listOf(false, false, false),
            listOf(false, false, false)
        )
        board.forEach { row, col ->
            assertEquals(0, board.liveNeigboursCount(row, col))
        }
    }

    @Test
    fun `liveNeigboursCount 1`() {
        val board = listOf(
            listOf(false, false, false),
            listOf(false, true, false),
            listOf(false, false, false)
        )
        board.forEach { row, col ->
            if (row == 1 && col == 1)
                assertEquals(0, board.liveNeigboursCount(row, col), "at $row $col")
            else
                assertEquals(1, board.liveNeigboursCount(row, col), "at $row $col")
        }
    }
}

typealias Board = List<List<Boolean>>

val Board.width: Int get() = this[0].size
val Board.height: Int get() = this.size
fun Board.at(row: Int, col: Int): Boolean = this[row][col]

fun Board.liveNeigboursCount(row: Int, col: Int): Int =
    listNeighbours(row, col).map { (row, col) ->
        if (at(row, col)) 1 else 0
    }.sum()

fun Board.rowPlus(row: Int, offset: Int) = if (row + offset < 0) height - 1 else (row + offset) % width
fun Board.colPlus(col: Int, offset: Int) = if (col + offset < 0) width - 1 else (col + offset) % height
fun Board.listNeighbours(row: Int, col: Int): List<Pair<Int, Int>> =
    listOf(
        rowPlus(row, -1) to colPlus(col, -1), rowPlus(row, -1) to colPlus(col, +0), rowPlus(row, -1) to colPlus(col, +1),
        rowPlus(row, +0) to colPlus(col, -1), /*                                */  rowPlus(row, +0) to colPlus(col, +1),
        rowPlus(row, +1) to colPlus(col, -1), rowPlus(row, +1) to colPlus(col, +0), rowPlus(row, +1) to colPlus(col, +1),
    ).also(::println)

fun Board.printed(): String =
    (0 until height).joinToString("\n") { row ->
        (0 until width).joinToString("") { col ->
            if (at(row, col)) "*" else "."
        }
    }

inline fun Board.forEach(f: (row: Int, col: Int) -> Unit) =
    (0 until width).forEach { col ->
        (0 until height).forEach { row ->
            f(row, col)
        }
    }

fun boardOf(w: Int, h: Int): Board = (0 until h).map { (0 until w).map { false } }


