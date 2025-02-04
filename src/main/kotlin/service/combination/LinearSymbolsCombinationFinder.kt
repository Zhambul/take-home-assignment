package service.combination

import config.WinCombination
import domain.Matrix
import domain.WonCombination

class LinearSymbolsCombinationFinder : OneCombinationFinder {

    override fun `when`(): String {
        return "linear_symbols"
    }

    override fun find(combination: WinCombination, combinationName: String, matrix: Matrix): List<WonCombination> {
        val coveredAreas = combination.coveredAreas
            ?: throw IllegalArgumentException("No covered areas found for linear symbols")

        return coveredAreas.mapNotNull { coveredArea ->
            val symbols = coveredArea.map { matrix.symbols[it] }
            val symbolsCount = symbols.groupingBy { it }.eachCount()
            if (symbolsCount.keys.size == 1) {
                WonCombination(
                    symbols[0]!!,
                    combinationName,
                    combination.rewardMultiplier,
                    combination.`when`,
                    coveredArea.size
                )
            } else {
                null
            }
        }
    }
}