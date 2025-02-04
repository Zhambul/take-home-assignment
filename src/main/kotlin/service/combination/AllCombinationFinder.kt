package service.combination

import config.GameConfig
import domain.Matrix
import domain.WonCombination

class AllCombinationFinder (
    private val finders: List<OneCombinationFinder>
): CombinationFinder {

    override fun findWinningCombinations(matrix: Matrix, config: GameConfig): List<WonCombination> {
        return config.winCombinations.flatMap { (combinationName, combination) ->

            val finder = finders.first { finder -> finder.`when`() == combination.`when` }

            finder.find(combination, combinationName, matrix)
        }
    }
}