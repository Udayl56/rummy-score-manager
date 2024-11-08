class Rummy(private var playerCount: Int, private val pointsLimit: Int) {
    private val playerNames = listOf("Sakuni", "Yudhis", "Duryod", "Karnav", "Bhimaa", "Dushan", "Arjuna", "Nakula")
    private val playerScores = mutableMapOf<String, Int>()

    init {
        for (i in 0 until playerCount) {
            playerScores[playerNames[i]] = 0
        }
        println("Total Players: $playerCount")
        println("Points Limit: $pointsLimit")
        println("***********************************")
    }

    fun getPlayerNames(): Set<String> = playerScores.keys

    fun getPlayerScores(): Map<String, Int> = playerScores

    fun getEliminatedPlayers(): List<String> = playerScores.filter { it.value > pointsLimit }.keys.toList()

    fun updateScore(player: String, score: Int) {
        playerScores[player] = playerScores.getValue(player) + score
    }

    fun checkReEntryScore(): Int {
        var maxEligibleScore = 0
        for (score in playerScores.values) {
            if (score <= 175) maxEligibleScore = maxOf(maxEligibleScore, score)
            if (score in 176..pointsLimit) return 0
        }
        return maxEligibleScore
    }

    fun reEnterPlayer(player: String, score: Int) {
        playerScores[player] = score
    }

    fun removePlayer(player: String) {
        playerCount--
        playerScores.remove(player)
    }

    fun remainingPlayers(): Int = playerCount

    fun getWinner(): String = playerScores.keys.firstOrNull() ?: "No winner"
}
