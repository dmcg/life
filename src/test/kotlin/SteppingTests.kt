import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class SteppingTests {
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


