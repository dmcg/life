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

