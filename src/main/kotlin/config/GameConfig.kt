package config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameConfig(
    val columns: Int,
    val rows: Int,
    val symbols: Map<String, Symbol>,
    val probabilities: Probabilities,
    @SerialName("win_combinations") val winCombinations: Map<String, WinCombination>
)

@Serializable
data class Symbol(
    @SerialName("reward_multiplier") val rewardMultiplier: Double? = null,
    val type: String,
    val impact: String? = null,
    val extra: Int? = null
)

@Serializable
data class Probabilities(
    @SerialName("standard_symbols") val standardSymbols: List<StandardSymbolProbability>,
    @SerialName("bonus_symbols") val bonusSymbols: BonusSymbolProbability
)

@Serializable
data class StandardSymbolProbability(
    val column: Int,
    val row: Int,
    val symbols: Map<String, Int>
)

@Serializable
data class BonusSymbolProbability(
    val symbols: Map<String, Int>
)

@Serializable
data class WinCombination(
    @SerialName("reward_multiplier") val rewardMultiplier: Double,
    val `when`: String,
    val count: Int? = null,
    val group: String? = null,
    @SerialName("covered_areas") val coveredAreas: List<List<String>>? = null
)
