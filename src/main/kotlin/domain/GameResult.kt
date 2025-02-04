package domain

import kotlinx.serialization.Serializable

@Serializable
data class GameResult(
    val matrix: List<List<String>>,
    val reward: Double,
    val appliedWinningCombinations: Map<String, List<String>>,
    val appliedBonusSymbol: String?
)

