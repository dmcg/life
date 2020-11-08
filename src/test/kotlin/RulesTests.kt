import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RulesTests {

    @Test
    fun `a dead cell with 3 live neighbours is born`() {
        assertEquals(true, shouldBeAlive(isAliveNow = false, liveNeighbours = 3))
    }

    @Test
    fun `a dead cell with not 3 live neighbours stays dead`() {
        assertEquals(false, shouldBeAlive(isAliveNow = false, liveNeighbours = 0))
        assertEquals(false, shouldBeAlive(isAliveNow = false, liveNeighbours = 1))
        assertEquals(false, shouldBeAlive(isAliveNow = false, liveNeighbours = 2))
        assertEquals(false, shouldBeAlive(isAliveNow = false, liveNeighbours = 4))
        assertEquals(false, shouldBeAlive(isAliveNow = false, liveNeighbours = 5))
        assertEquals(false, shouldBeAlive(isAliveNow = false, liveNeighbours = 6))
        assertEquals(false, shouldBeAlive(isAliveNow = false, liveNeighbours = 7))
        assertEquals(false, shouldBeAlive(isAliveNow = false, liveNeighbours = 8))
    }

    @Test
    fun `a live cell with 0 or 1 live neighbours dies`() {
        assertEquals(false, shouldBeAlive(isAliveNow = true, liveNeighbours = 0))
        assertEquals(false, shouldBeAlive(isAliveNow = true, liveNeighbours = 1))
    }

    @Test
    fun `a live cell with 4 or more live neighbours dies`() {
        assertEquals(false, shouldBeAlive(isAliveNow = true, liveNeighbours = 4))
        assertEquals(false, shouldBeAlive(isAliveNow = true, liveNeighbours = 5))
        assertEquals(false, shouldBeAlive(isAliveNow = true, liveNeighbours = 6))
        assertEquals(false, shouldBeAlive(isAliveNow = true, liveNeighbours = 7))
        assertEquals(false, shouldBeAlive(isAliveNow = true, liveNeighbours = 8))
    }

    @Test
    fun `a live cell with 2 or 3 live neighbours lives`() {
        assertEquals(true, shouldBeAlive(isAliveNow = true, liveNeighbours = 2))
        assertEquals(true, shouldBeAlive(isAliveNow = true, liveNeighbours = 3))
    }

}