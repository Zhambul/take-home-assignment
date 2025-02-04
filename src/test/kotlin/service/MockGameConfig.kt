package service

import config.*

object MockGameConfig {

    fun create(): GameConfig {
        val symbols = mapOf(
            "A" to Symbol(
                rewardMultiplier = 5.0,
                type = "standard"
            ),
            "B" to Symbol(
                rewardMultiplier = 3.0,
                type = "standard"
            ),
            "C" to Symbol(
                rewardMultiplier = 2.5,
                type = "standard"
            ),
            "D" to Symbol(
                rewardMultiplier = 2.0,
                type = "standard"
            ),
            "E" to Symbol(
                rewardMultiplier = 1.2,
                type = "standard"
            ),
            "F" to Symbol(
                rewardMultiplier = 1.0,
                type = "standard"
            ),
            "10x" to Symbol(
                rewardMultiplier = 10.0,
                type = "bonus",
                impact = "multiply_reward"
            ),
            "5x" to Symbol(
                rewardMultiplier = 5.0,
                type = "bonus",
                impact = "multiply_reward"
            ),
            "+1000" to Symbol(
                type = "bonus",
                impact = "extra_bonus",
                extra = 1000
            ),
            "+500" to Symbol(
                type = "bonus",
                impact = "extra_bonus",
                extra = 500
            ),
            "MISS" to Symbol(
                type = "bonus",
                impact = "miss"
            )
        )

        val standardSymbolProbabilities = mutableListOf<StandardSymbolProbability>()

        for (column in 0..2) {
            for (row in 0..2) {
                standardSymbolProbabilities.add(
                    StandardSymbolProbability(
                        column = column,
                        row = row,
                        symbols = mapOf(
                            "A" to 1,
                            "B" to 2,
                            "C" to 3,
                            "D" to 4,
                            "E" to 5,
                            "F" to 6
                        )
                    )
                )
            }
        }

        val bonusSymbolProbability = BonusSymbolProbability(
            symbols = mapOf(
                "10x" to 1,
                "5x" to 2,
                "+1000" to 3,
                "+500" to 4,
                "MISS" to 5
            )
        )

        val probabilities = Probabilities(
            standardSymbols = standardSymbolProbabilities,
            bonusSymbols = bonusSymbolProbability
        )

        val winCombinations = mapOf(
            "same_symbol_3_times" to WinCombination(
                rewardMultiplier = 1.0,
                `when` = "same_symbols",
                count = 3,
                group = "same_symbols"
            ),
            "same_symbol_4_times" to WinCombination(
                rewardMultiplier = 1.5,
                `when` = "same_symbols",
                count = 4,
                group = "same_symbols"
            ),
            "same_symbol_5_times" to WinCombination(
                rewardMultiplier = 2.0,
                `when` = "same_symbols",
                count = 5,
                group = "same_symbols"
            ),
            "same_symbol_6_times" to WinCombination(
                rewardMultiplier = 3.0,
                `when` = "same_symbols",
                count = 6,
                group = "same_symbols"
            ),
            "same_symbol_7_times" to WinCombination(
                rewardMultiplier = 5.0,
                `when` = "same_symbols",
                count = 7,
                group = "same_symbols"
            ),
            "same_symbol_8_times" to WinCombination(
                rewardMultiplier = 10.0,
                `when` = "same_symbols",
                count = 8,
                group = "same_symbols"
            ),
            "same_symbol_9_times" to WinCombination(
                rewardMultiplier = 20.0,
                `when` = "same_symbols",
                count = 9,
                group = "same_symbols"
            ),
            "same_symbols_horizontally" to WinCombination(
                rewardMultiplier = 2.0,
                `when` = "linear_symbols",
                group = "horizontally_linear_symbols",
                coveredAreas = listOf(
                    listOf("0:0", "0:1", "0:2"),
                    listOf("1:0", "1:1", "1:2"),
                    listOf("2:0", "2:1", "2:2")
                )
            ),
            "same_symbols_vertically" to WinCombination(
                rewardMultiplier = 2.0,
                `when` = "linear_symbols",
                group = "vertically_linear_symbols",
                coveredAreas = listOf(
                    listOf("0:0", "1:0", "2:0"),
                    listOf("0:1", "1:1", "2:1"),
                    listOf("0:2", "1:2", "2:2")
                )
            ),
            "same_symbols_diagonally_left_to_right" to WinCombination(
                rewardMultiplier = 5.0,
                `when` = "linear_symbols",
                group = "ltr_diagonally_linear_symbols",
                coveredAreas = listOf(
                    listOf("0:0", "1:1", "2:2")
                )
            ),
            "same_symbols_diagonally_right_to_left" to WinCombination(
                rewardMultiplier = 5.0,
                `when` = "linear_symbols",
                group = "rtl_diagonally_linear_symbols",
                coveredAreas = listOf(
                    listOf("0:2", "1:1", "2:0")
                )
            )
        )

        return GameConfig(
            columns = 3,
            rows = 3,
            symbols = symbols,
            probabilities = probabilities,
            winCombinations = winCombinations
        )
    }
}