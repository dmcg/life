import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class BoardTests {

    @Test
    fun `new board has specified size`() {
        val board = boardOf(width = 4, height = 3)
        assertEquals(4, board.width)
        assertEquals(3, board.height)
    }

    @Test
    fun `new board is empty`() {
        val board = boardOf(width = 4, height = 3)
        board.forEach { row, col ->
            assertFalse(board.at(row, col))
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
    fun `liveNeighboursCount 0`() {
        val board = boardOf(
            "...",
            "...",
            "...",
        )
        board.forEach { row, col ->
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
        board.forEach { row, col ->
            if (row == 1 && col == 1)
                assertEquals(0, board.liveNeighboursCount(row, col), "at $row $col")
            else
                assertEquals(1, board.liveNeighboursCount(row, col), "at $row $col")
        }
    }
}

typealias Board = List<List<Boolean>>

val Board.width: Int get() = this[0].size
val Board.height: Int get() = this.size
fun Board.at(row: Int, col: Int): Boolean = this[row][col]

fun Board.liveNeighboursCount(row: Int, col: Int): Int =
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

fun boardOf(width: Int, height: Int): Board = (0 until height).map { (0 until width).map { false } }

fun boardOf(s: String): Board = boardOf(s.lines())
fun boardOf(vararg lines: String) = boardOf(lines.toList())
fun boardOf(lines: List<String>): List<List<Boolean>> {
    val width = lines[0].length
    val height = lines.size
    return (0 until height).map { row ->
        (0 until width).map { col ->
            (lines[row][col] == '*')
        }
    }
}


