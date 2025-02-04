package service.combination

import config.GameConfig
import domain.Matrix
import domain.WonCombination

interface CombinationFinder{

    fun findWinningCombinations(matrix: Matrix, config: GameConfig): List<WonCombination>
}

