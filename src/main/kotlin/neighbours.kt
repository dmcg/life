

fun neighboursFor(row: Int, col: Int, width: Int, height: Int): List<Pair<Int, Int>> {
    check(row in 0 until height)
    check(col in 0 until width)

    val up = if (row == 0) null else row - 1
    val down = if (row == height - 1) null else row + 1
    val left = if (col == 0) null else col - 1
    val right = if (col == width - 1) null else col + 1
    return listOf(
        up to left, up to col, up to right,
        row to left, null to null, row to right,
        down to left, down to col, down to right
    ).filter { (row, col) -> row != null && col != null } as List<Pair<Int, Int>>
}