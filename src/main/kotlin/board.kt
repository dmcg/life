
typealias Board = List<List<Boolean>>

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

fun Board.printed(): String =
    map { row, col ->
        if (isAliveAt(row, col)) "*" else "."
    }.joinToString("\n") { row ->
        row.joinToString("")
    }

val Board.width: Int get() = this[0].size
val Board.height: Int get() = this.size
fun Board.isAliveAt(row: Int, col: Int): Boolean = this[row][col]

fun Board.stepOn(): Board =
    map { row, col ->
        shouldBeAlive(isAliveAt(row, col), liveNeighboursCount(row, col))
    }

internal fun Board.liveNeighboursCount(row: Int, col: Int): Int =
    listNeighbours(row, col).count { (row, col) -> isAliveAt(row, col) }

private fun Board.listNeighbours(row: Int, col: Int): List<Pair<Int, Int>> =
    neighboursFor(row, col, width, height)

inline fun <R> Board.map(f: (row: Int, col: Int) -> R): List<List<R>> =
    (0 until height).map { row ->
        (0 until width).map { col ->
            f(row, col)
        }
    }

