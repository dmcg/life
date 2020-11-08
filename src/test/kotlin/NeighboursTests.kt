import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.junit5.JUnit5Asserter.assertEquals

class NeighboursTests {

    @Test
    fun `single cell has no neighbours`() {
        assertEquals(
            emptyList(),
            neighboursFor(row = 0, col = 0, width = 1, height = 1)
        )
    }

    @Test
    fun `3 in a row`() {
        assertEquals(
            listOf(0 to 1),
            neighboursFor(row = 0, col = 0, width = 3, height = 1)
        )
        assertEquals(
            listOf(0 to 0, 0 to 2),
            neighboursFor(row = 0, col = 1, width = 3, height = 1)
        )
        assertEquals(
            listOf(0 to 1),
            neighboursFor(row = 0, col = 2, width = 3, height = 1)
        )
    }
}

fun neighboursFor(row: Int, col: Int, width: Int, height: Int): List<Pair<Int, Int>> {
    check(row in 0 until height)
    check(col in 0 until width)

    val up = if (row == 0) null else row - 1
    val down = if (row == height - 1) null else row + 1
    val left = if (col == 0) null else col - 1
    val right = if (col == width  - 1) null else col + 1
    return listOf(
        up to left, up to col, up to right,
        row to left, null to null, row to right,
        down to left, down to col, down to right
    ).filter { (row, col) -> row != null && col != null} as List<Pair<Int, Int>>
}

