package service

import org.junit.jupiter.api.Test
import service.combination.AllCombinationFinder
import service.combination.LinearSymbolsCombinationFinder
import service.combination.SameSymbolsCombinationFinder

class AllCombinationFinderTestFinder {

    private val target = AllCombinationFinder(
        listOf(
            SameSymbolsCombinationFinder(),
            LinearSymbolsCombinationFinder()
        )
    )

    private val testConfig = MockGameConfig.create()

    @Test
    fun `should only find same_symbol_3_times combination`() {
        // GIVEN
        val matrix = MockMatrix.from(
            listOf(
                listOf("A", "B", "A"),
                listOf("A", "C", "D"),
                listOf("E", "F", "MISS")
            )
        )

        // WHEN
        val result = target.findWinningCombinations(matrix, testConfig)

        // THEN
        assert(result.size == 1)
        assert(result.first().symbol == "A")
        assert(result.first().combinationName == "same_symbol_3_times")
    }

    @Test
    fun `should only find same_symbol_4_times combination`() {
        // GIVEN
        val matrix = MockMatrix.from(
            listOf(
                listOf("A", "B", "A"),
                listOf("A", "C", "A"),
                listOf("E", "F", "MISS")
            )
        )

        // WHEN
        val result = target.findWinningCombinations(matrix, testConfig)

        // THEN
        assert(result.size == 1)
        assert(result.first().symbol == "A")
        assert(result.first().combinationName == "same_symbol_4_times")
    }

    @Test
    fun `should only find same_symbol_5_times combination`() {
        // GIVEN
        val matrix = MockMatrix.from(
            listOf(
                listOf("A", "B", "A"),
                listOf("A", "C", "A"),
                listOf("E", "A", "MISS")
            )
        )

        // WHEN
        val result = target.findWinningCombinations(matrix, testConfig)

        // THEN
        assert(result.size == 1)
        assert(result.first().symbol == "A")
        assert(result.first().combinationName == "same_symbol_5_times")
    }


    @Test
    fun `should find same_symbol_3_times and same_symbols_horizontally combinations`() {
        // GIVEN
        val matrix = MockMatrix.from(
            listOf(
                listOf("A", "A", "A"),
                listOf("B", "C", "D"),
                listOf("E", "F", "MISS")
            )
        )

        // WHEN
        val result = target.findWinningCombinations(matrix, testConfig)

        // THEN
        assert(result.size == 2)

        assert(result.filter { it.symbol == "A" && it.combinationName == "same_symbol_3_times" }.size == 1)
        assert(result.filter { it.symbol == "A" && it.combinationName == "same_symbols_horizontally" }.size == 1)
    }

    @Test
    fun `should find same_symbol_3_times and same_symbols_vertically combinations`() {
        // GIVEN
        val matrix = MockMatrix.from(
            listOf(
                listOf("A", "B", "E"),
                listOf("A", "C", "D"),
                listOf("A", "F", "MISS")
            )
        )

        // WHEN
        val result = target.findWinningCombinations(matrix, testConfig)

        // THEN
        assert(result.size == 2)

        assert(result.filter { it.symbol == "A" && it.combinationName == "same_symbol_3_times" }.size == 1)
        assert(result.filter { it.symbol == "A" && it.combinationName == "same_symbols_vertically" }.size == 1)
    }

    @Test
    fun `should find same_symbol_3_times and same_symbols_diagonally_left_to_right combinations`() {
        // GIVEN
        val matrix = MockMatrix.from(
            listOf(
                listOf("A", "B", "E"),
                listOf("C", "A", "D"),
                listOf("MISS", "F", "A")
            )
        )

        // WHEN
        val result = target.findWinningCombinations(matrix, testConfig)

        // THEN
        assert(result.size == 2)

        assert(result.filter { it.symbol == "A" && it.combinationName == "same_symbol_3_times" }.size == 1)
        assert(result.filter { it.symbol == "A" && it.combinationName == "same_symbols_diagonally_left_to_right" }.size == 1)

    }

    @Test
    fun `should find same_symbol_3_times and same_symbols_diagonally_right_to_left combinations`() {
        // GIVEN
        val matrix = MockMatrix.from(
            listOf(
                listOf("E", "B", "A"),
                listOf("C", "A", "D"),
                listOf("A", "F", "MISS")
            )
        )

        // WHEN
        val result = target.findWinningCombinations(matrix, testConfig)

        // THEN
        assert(result.size == 2)

        assert(result.filter { it.symbol == "A" && it.combinationName == "same_symbol_3_times" }.size == 1)
        assert(result.filter { it.symbol == "A" && it.combinationName == "same_symbols_diagonally_right_to_left" }.size == 1)
    }

    @Test
    fun `should find 2 x  same_symbol_3_times combinations`() {
        // GIVEN
        val matrix = MockMatrix.from(
            listOf(
                listOf("A", "B", "A"),
                listOf("A", "B", "D"),
                listOf("B", "F", "MISS")
            )
        )

        // WHEN
        val result = target.findWinningCombinations(matrix, testConfig)

        // THEN
        assert(result.size == 2)
        assert(result.filter { it.symbol == "A" && it.combinationName == "same_symbol_3_times" }.size == 1)
        assert(result.filter { it.symbol == "B" && it.combinationName == "same_symbol_3_times" }.size == 1)
    }

    @Test
    fun `should find 2 x same_symbol_3_times and 2 x same_symbols_horizontally  combinations`() {
        // GIVEN
        val matrix = MockMatrix.from(
            listOf(
                listOf("A", "A", "A"),
                listOf("B", "B", "B"),
                listOf("C", "F", "MISS")
            )
        )

        // WHEN
        val result = target.findWinningCombinations(matrix, testConfig)

        // THEN
        assert(result.size == 4)
        assert(result.filter { it.symbol == "A" && it.combinationName == "same_symbol_3_times" }.size == 1)
        assert(result.filter { it.symbol == "B" && it.combinationName == "same_symbol_3_times" }.size == 1)

        assert(result.filter { it.symbol == "A" && it.combinationName == "same_symbols_horizontally" }.size == 1)
        assert(result.filter { it.symbol == "B" && it.combinationName == "same_symbols_horizontally" }.size == 1)
    }

    @Test
    fun `should find | 1 x same_symbol_8_times | 2 x same_symbols_horizontally | 2 x same_symbols_vertically |1 x same_symbols_diagonally_right_to_left | combinations`() {
        // GIVEN
        val matrix = MockMatrix.from(
            listOf(
                listOf("A", "A", "A"),
                listOf("A", "A", "A"),
                listOf("A", "A", "MISS")
            )
        )

        // WHEN
        val result = target.findWinningCombinations(matrix, testConfig)

        // THEN
        assert(result.size == 6)

        assert(result.filter { it.symbol == "A" && it.combinationName == "same_symbol_8_times" }.size == 1)
        assert(result.filter { it.symbol == "A" && it.combinationName == "same_symbols_horizontally" }.size == 2)
        assert(result.filter { it.symbol == "A" && it.combinationName == "same_symbols_vertically" }.size == 2)
        assert(result.filter { it.symbol == "A" && it.combinationName == "same_symbols_diagonally_right_to_left" }.size == 1)
    }

}