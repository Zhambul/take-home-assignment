package domain

import kotlinx.serialization.Serializable

@Serializable
data class WonCombination(
    val symbol: String,
    val combinationName: String,
    val rewardMultiplier: Double,
    val `when`: String,
    val count: Int? = null,
    val group: String? = null,
    val coveredAreas: List<List<String>>? = null
)
