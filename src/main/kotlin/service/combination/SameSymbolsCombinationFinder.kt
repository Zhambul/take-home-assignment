package service.combination

import config.WinCombination
import domain.Matrix
import domain.WonCombination

class SameSymbolsCombinationFinder : OneCombinationFinder {

    override fun `when`(): String {
        return "same_symbols"
    }

    override fun find(combination: WinCombination, combinationName: String, matrix: Matrix): List<WonCombination> {
        val symbolsCount = matrix.symbols.values.groupingBy { it }.eachCount()
        val winningCount = combination.count!!

        return symbolsCount
            .mapNotNull { (symbol, symbolCount) ->
                if (symbolCount == winningCount) {
                    WonCombination(
                        symbol,
                        combinationName,
                        combination.rewardMultiplier,
                        combination.`when`,
                        winningCount
                    )
                } else {
                    null
                }
            }
    }
}
