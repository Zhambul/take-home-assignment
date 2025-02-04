package service

import config.GameConfig
import domain.WonCombination
import java.math.BigDecimal

interface RewardCalculator {
    fun calculateReward(
        config: GameConfig,
        combinations: List<WonCombination>,
        bonus: String,
        bettingAmount: BigDecimal
    ): BigDecimal
}

class DefaultRewardCalculator : RewardCalculator {

    override fun calculateReward(
        config: GameConfig,
        combinations: List<WonCombination>,
        bonus: String,
        bettingAmount: BigDecimal
    ): BigDecimal {

        if (combinations.isEmpty()) {
            return BigDecimal.ZERO
        }

        val standardReward = calculateStandardReward(combinations, config, bettingAmount)

        return applyBonus(config, bonus, standardReward)
    }

    private fun applyBonus(config: GameConfig, bonus: String, totalReward: BigDecimal): BigDecimal {
        val bonusSymbol = config.symbols[bonus]!!

        return when (bonusSymbol.impact) {
            "multiply_reward" -> {
                totalReward * BigDecimal.valueOf(bonusSymbol.rewardMultiplier!!)
            }

            "extra_bonus" -> {
                totalReward + BigDecimal.valueOf(bonusSymbol.extra!!.toLong())
            }

            "miss" -> {
                totalReward
            }

            else -> {
                throw IllegalArgumentException("Unknown bonus impact: ${bonusSymbol.impact}")
            }
        }
    }

    private fun calculateStandardReward(
        combinations: List<WonCombination>,
        config: GameConfig,
        betAmount: BigDecimal
    ): BigDecimal {
        var totalReward = BigDecimal.ZERO

        for ((symbol, symbolCombinations) in combinations.groupBy { it.symbol }) {
            val rewardMultiplier = config.symbols[symbol]!!.rewardMultiplier!!
            var symbolReward = betAmount * BigDecimal.valueOf(rewardMultiplier)

            for (winCombination in symbolCombinations) {
                symbolReward *= BigDecimal.valueOf(winCombination.rewardMultiplier)
            }

            totalReward += symbolReward
        }
        return totalReward
    }
}