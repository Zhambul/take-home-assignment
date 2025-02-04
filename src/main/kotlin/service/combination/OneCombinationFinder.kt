package service.combination

import config.WinCombination
import domain.Matrix
import domain.WonCombination

interface OneCombinationFinder {

    fun `when`(): String

    fun find(combination: WinCombination, combinationName: String, matrix: Matrix): List<WonCombination>
}