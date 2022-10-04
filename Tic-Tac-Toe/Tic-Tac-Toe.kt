class TicTacToe(firstPlayerName: String, secondPlayerName: String) {
    private var map:MutableList<Char> = mutableListOf(
        ' ', ' ', ' ',
        ' ', ' ', ' ',
        ' ', ' ', ' ')
    private var fName: String = firstPlayerName
    private var sName: String = secondPlayerName

    private fun printMap() {
        for (i in 0..2)
            println("|${map[i * 3]}|${map[i * 3 + 1]}|${map[i * 3 + 2]}|")
    }

    private fun getWinnerNum(sim: Char) = (if (sim == 'X') 1 else 2)

    private fun readInts() = readLine()!!.split(' ').map { it.toInt() }

    private fun winner(): Int {
        for (i in 0..2) {
            if (map[i*3] != ' ')
                if ((map[i*3] == map[i*3 + 1]) && (map[i*3 + 1] == map[i*3 + 2]))
                    return getWinnerNum(map[i*3])
            if (map[i] != ' ')
                if ((map[i] == map[i+3]) and (map[i+3] == map[i+6]))
                    return getWinnerNum(map[i])
        }

        if (map[4] == ' ')
            return 0
        if ((map[0] == map[4]) && (map[4] == map[8]))
            return getWinnerNum(map[4])
        if ((map[2] == map[4]) && (map[4] == map[6]))
            return getWinnerNum(map[4])
        return 0
    }

    fun start() {
        var playerNum: Int = (0..1).random()
        println("The main rule of game: wrote coordinates as 'x, y'")
        println("Coordinates range is 0..2")
        println("Game stars on ${(if (playerNum == 0) fName else sName)}")
        while (winner() == 0) {
            var coords: List<Int> = listOf(-1, -1)
            try {
                print("Player ${playerNum + 1} move: ")
                coords = readInts()
            } catch (e: Exception) {
                println("Please, try again!")
            }
            while (!((coords[0] in 0..2) && (coords[1] in 0..2) && (map[coords[0] * 3 + coords[1]] == ' '))) {
                try {
                    print("Player ${playerNum + 1} move: ")
                    coords = readInts()
                } catch (e: Exception) {
                    println("Please, try again!")
                }
            }
            map[coords[0] * 3 + coords[1]] = (if (playerNum == 0) 'X' else '0')
            playerNum = (playerNum + 1) % 2
            printMap()
        }
        println("Winner is ${if (winner() == 1) fName else sName}")
    }
}

fun main() {
    val game: TicTacToe = TicTacToe(readln(), readln())
    game.start()
}