import java.lang.Exception

fun main() {
    println("Game Started")

    try {
        print("Enter total players:")
        val totalPlayers:Int = readln().toInt()
        val pointsLimit = 200

        val game = Rummy(totalPlayers, pointsLimit)

        var turnCount = 1

        while (true) {
            println("\nTurn $turnCount: Enter scores")

            for (player in game.getPlayerNames()) {
                print("$player's score: ")
                val score = readln().toInt()
                game.updateScore(player, score)
            }

            println("Current Total Scores: ${game.getPlayerScores()}")

            val eliminatedPlayers = game.getEliminatedPlayers()
            if (eliminatedPlayers.isNotEmpty()) {

                // check entry available

                if (game.remainingPlayers()-eliminatedPlayers.size >= 3) {
                    val entryScore = game.checkReEntryScore()
                    if (entryScore != 0 && entryScore <= 175) {
                        println("\nRe-entry available at $entryScore. Enter 1 for re-entry or 0 to decline.")
                        for (player in eliminatedPlayers) {
                            print("$player: ")
                            val reEntryChoice = readln().toInt()
                            if (reEntryChoice == 1) {
                                game.reEnterPlayer(player, entryScore)
                            } else {
                                game.removePlayer(player)
                            }
                        }
                        game.getPlayerScores()
                    } else {
                        eliminatedPlayers.forEach { player ->
                            println("$player is out of the game.")
                            game.removePlayer(player)
                        }
                    }
                } else if (eliminatedPlayers.size == game.remainingPlayers() - 2) {
                    eliminatedPlayers.forEach { player ->
                        println("$player is out of the game.")
                        game.removePlayer(player)
                    }
                }
                else if (eliminatedPlayers.size == game.remainingPlayers() - 1) {
                    eliminatedPlayers.forEach { player ->
                        println("$player is out of the game.")
                        game.removePlayer(player)
                    }
                    println("Congratulations ${game.getWinner()}! You have won the game.\nGame finished")
                    break

                }
            }



            turnCount++
        }
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}
