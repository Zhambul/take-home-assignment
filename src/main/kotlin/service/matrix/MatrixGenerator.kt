package service.matrix

import config.BonusSymbolProbability
import config.GameConfig
import config.StandardSymbolProbability
import domain.Matrix
import kotlin.random.Random

interface MatrixGenerator {

    fun generate(config: GameConfig): Matrix
}

class RandomMatrixGenerator : MatrixGenerator {

    private val random = Random.Default

    override fun generate(config: GameConfig): Matrix {
        val symbols = mutableMapOf<String, String>()

        generateStandardSymbols(config, symbols)

        val bonus = generateBonusSymbol(config, symbols)

        return Matrix(symbols, bonus)
    }

    private fun generateBonusSymbol(
        config: GameConfig,
        symbols: MutableMap<String, String>
    ): String {
        val randomColumnIndex = random.nextInt(config.columns)
        val randomRowIndex = random.nextInt(config.rows)

        val bonus = generateBonusSymbol(config.probabilities.bonusSymbols)

        symbols["${randomColumnIndex}:${randomRowIndex}"] = bonus
        return bonus
    }

    private fun generateStandardSymbols(config: GameConfig, symbols: MutableMap<String, String>) {
        for (column in 0 until config.columns) {
            for (row in 0 until config.rows) {
                symbols["${column}:${row}"] = generateStandardSymbol(config.probabilities.standardSymbols, column, row)
            }
        }
    }

    private fun generateStandardSymbol(probabilities: List<StandardSymbolProbability>, column: Int, row: Int): String {
        val probability = probabilities.find { it.column == column && it.row == row }
            ?: throw IllegalArgumentException("No probability found for column $column and row $row")

        return generate(probability.symbols)
    }

    private fun generateBonusSymbol(bonusSymbols: BonusSymbolProbability): String {
        return generate(bonusSymbols.symbols)
    }

    private fun generate(symbolProbabilities: Map<String, Int>): String {
        val totalProbability = symbolProbabilities.values.sum()
        val randomValue = random.nextInt(totalProbability)

        var cumulativeProbability = 0
        for ((symbol, symbolProbability) in symbolProbabilities) {
            cumulativeProbability += symbolProbability

            if (randomValue < cumulativeProbability) {
                return symbol
            }
        }

        throw IllegalStateException("Failed to generate symbol, random value: $randomValue, total probability: $totalProbability")
    }

}