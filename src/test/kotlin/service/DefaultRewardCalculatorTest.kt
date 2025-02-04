package service

import domain.WonCombination
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class DefaultRewardCalculatorTest {

    val target = DefaultRewardCalculator()

    val testConfig = MockGameConfig.create()

    @Test
    fun `should return 0 when there is no winning combination with 10x bonus`() {
        // GIVEN
        val wonCombinations = emptyList<WonCombination>()
        val bonus = "10x"
        val betAmount = BigDecimal.valueOf(100)

        // WHEN
        val result = target.calculateReward(testConfig, wonCombinations, bonus, betAmount)

        // THEN
        // 0 * 1 * 10 = 0
        assertEquals(BigDecimal.valueOf(0).stripTrailingZeros(), result.stripTrailingZeros())

    }

    @Test
    fun `should return 5000 for A same_symbol_3_times with 10x bonus`() {
        // GIVEN
        val wonCombinations = listOf(
            WonCombination(
                "A",
                "same_symbol_3_times",
                1.0,
                "same_symbols",
                3,
                "same_symbols"
            ),
        )

        val bonus = "10x"
        val betAmount = BigDecimal.valueOf(100)

        // WHEN
        val result = target.calculateReward(testConfig, wonCombinations, bonus, betAmount)

        // THEN
        // 100 * 5 * 1.0 * 10 = 5000
        assertEquals(BigDecimal.valueOf(5000).stripTrailingZeros(), result.stripTrailingZeros())
    }

    @Test
    fun `should return 500 for A same_symbol_3_times with MISS bonus`() {
        // GIVEN
        val wonCombinations = listOf(
            WonCombination(
                "A",
                "same_symbol_3_times",
                1.0,
                "same_symbols",
                3,
                "same_symbols"
            ),
        )

        val bonus = "MISS"
        val betAmount = BigDecimal.valueOf(100)

        // WHEN
        val result = target.calculateReward(testConfig, wonCombinations, bonus, betAmount)

        // THEN
        // 100 * 5 * 1.0 = 500
        assertEquals(BigDecimal.valueOf(500).stripTrailingZeros(), result.stripTrailingZeros())
    }

    @Test
    fun `should return 1500 for A same_symbol_3_times with +1000 bonus`() {
        // GIVEN
        val wonCombinations = listOf(
            WonCombination(
                "A",
                "same_symbol_3_times",
                1.0,
                "same_symbols",
                3,
                "same_symbols"
            ),
        )

        val bonus = "+1000"
        val betAmount = BigDecimal.valueOf(100)

        // WHEN
        val result = target.calculateReward(testConfig, wonCombinations, bonus, betAmount)

        // THEN
        // 100 * 5 * 1.0 + 1000 = 1500
        assertEquals(BigDecimal.valueOf(1500).stripTrailingZeros(), result.stripTrailingZeros())
    }


    @Test
    fun `should return 2000 for A same_symbol_3_times and A same_symbols_horizontally with +1000 bonus`() {
        // GIVEN
        val wonCombinations = listOf(
            WonCombination(
                "A",
                "same_symbol_3_times",
                1.0,
                "same_symbols",
                3,
                "same_symbols"
            ),
            WonCombination(
                "A",
                "same_symbols_horizontally",
                2.0,
                "linear_symbols",
                null,
                "horizontally_linear_symbols"
            ),
        )

        val bonus = "+1000"
        val betAmount = BigDecimal.valueOf(100)

        // WHEN
        val result = target.calculateReward(testConfig, wonCombinations, bonus, betAmount)

        // THEN
        // 100 * 5 * 1.0 * 2 + 1000 = 2000
        assertEquals(BigDecimal.valueOf(2000).stripTrailingZeros(), result.stripTrailingZeros())
    }

    @Test
    fun `should return 2300 for A same_symbol_3_times and A same_symbols_horizontally and B same_symbol_3_times with +1000 bonus`() {
        // GIVEN
        val wonCombinations = listOf(
            WonCombination(
                "A",
                "same_symbol_3_times",
                1.0,
                "same_symbols",
                3,
                "same_symbols"
            ),
            WonCombination(
                "A",
                "same_symbols_horizontally",
                2.0,
                "linear_symbols",
                null,
                "horizontally_linear_symbols"
            ),
            WonCombination(
                "B",
                "same_symbol_3_times",
                1.0,
                "same_symbols",
                3,
                "same_symbols"
            ),
        )

        val bonus = "+1000"
        val betAmount = BigDecimal.valueOf(100)

        // WHEN
        val result = target.calculateReward(testConfig, wonCombinations, bonus, betAmount)

        // THEN
        // 100 * 5 * 1.0 * 2 + 100 * 3 * 1 + 1000 = 2300
        assertEquals(BigDecimal.valueOf(2300).stripTrailingZeros(), result.stripTrailingZeros())
    }
}