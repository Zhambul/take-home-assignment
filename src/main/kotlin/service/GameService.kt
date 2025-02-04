package service

import domain.GameResult
import domain.Matrix
import domain.WonCombination
import service.combination.CombinationFinder
import service.matrix.MatrixGenerator
import java.math.BigDecimal

interface GameService {
    fun play(bettingAmount: BigDecimal): GameResult
}

class DefaultGameService(
    private val configProvider: GameConfigProvider,
    private val matrixGenerator: MatrixGenerator,
    private val combinationFinder: CombinationFinder,
    private val rewardCalculator: RewardCalculator,
) : GameService {

    override fun play(bettingAmount: BigDecimal): GameResult {
        val config = configProvider.get()

        val matrix = matrixGenerator.generate(config)

        val combinations = combinationFinder.findWinningCombinations(matrix, config)

        val reward = rewardCalculator.calculateReward(config, combinations, matrix.bonus, bettingAmount)

        return GameResult(
            mapMatrix(matrix),
            reward.toDouble(),
            mapCombinations(combinations),
            matrix.bonus
        )
    }

    private fun mapCombinations(combinations: List<WonCombination>): Map<String, List<String>> {
        return combinations.groupBy { it.symbol }.mapValues { it.value.map { it.combinationName } }
    }

    private fun mapMatrix(matrix: Matrix): List<List<String>> {
        val columns = matrix.symbols.keys.map { it.split(":")[0].toInt() }.maxOrNull()!! + 1
        val rows = matrix.symbols.keys.map { it.split(":")[1].toInt() }.maxOrNull()!! + 1

        return (0 until columns).map { column ->
            (0 until rows).map { row ->
                matrix.symbols["$column:$row"]!!
            }
        }
    }
}