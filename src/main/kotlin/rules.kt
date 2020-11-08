
fun shouldBeAlive(isAliveNow: Boolean, liveNeighbours: Int) =
    when (isAliveNow) {
        true -> liveNeighbours == 2 || liveNeighbours == 3
        false -> liveNeighbours == 3
    }