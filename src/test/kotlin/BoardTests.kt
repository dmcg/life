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

    @Test
    fun `stepOn empty`() {
        val board = boardOf(3, 3)
        assertEquals(board, board.stepOn())
    }

    @Test
    fun `stepOn death`() {
        val board = boardOf(
            "...",
            ".*.",
            "...",
        )
        assertEquals(boardOf(3, 3), board.stepOn())
    }

    @Test
    fun `stepOn glider`() {
        val frame1 = boardOf(
            "..*.....",
            "...*....",
            ".***....",
            "........",
            "........",
            "........",
        )
        val frame2 = boardOf(
            "........",
            ".*.*....",
            "..**....",
            "..*.....",
            "........",
            "........",
        )
        assertEquals(frame2, frame1.stepOn())
        val frame3 = boardOf(
            "........",
            "...*....",
            ".*.*....",
            "..**....",
            "........",
            "........",
        )
        assertEquals(frame3, frame2.stepOn())
        val frame4 = boardOf(
            "........",
            "..*.....",
            "...**...",
            "..**....",
            "........",
            "........",
        )
        assertEquals(frame4, frame3.stepOn())
        val frame5 = boardOf(
            "........",
            "...*....",
            "....*...",
            "..***...",
            "........",
            "........",
        )
        assertEquals(frame5, frame4.stepOn())
    }

}

private fun Board.stepOn(): Board =
    map { row, col ->
        shouldBeAlive(isAliveAt(row, col), liveNeighboursCount(row, col))
    }

typealias Board = List<List<Boolean>>

val Board.width: Int get() = this[0].size
val Board.height: Int get() = this.size
fun Board.isAliveAt(row: Int, col: Int): Boolean = this[row][col]

fun Board.liveNeighboursCount(row: Int, col: Int): Int =
    listNeighbours(row, col).count { (row, col) -> isAliveAt(row, col) }

fun Board.listNeighbours(row: Int, col: Int): List<Pair<Int, Int>> =
    neighboursFor(row, col, width, height)


fun Board.printed(): String =
    map { row, col ->
        if (isAliveAt(row, col)) "*" else "."
    }.joinToString("\n") { row ->
        row.joinToString("")
    }

inline fun <R> Board.map(f: (row: Int, col: Int) -> R): List<List<R>> =
    (0 until height).map { row ->
        (0 until width).map { col ->
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


