import domain.GameResult
import kotlinx.serialization.json.Json
import service.*
import service.combination.AllCombinationFinder
import service.combination.CombinationFinder
import service.combination.LinearSymbolsCombinationFinder
import service.combination.SameSymbolsCombinationFinder
import service.game.DefaultGameService
import service.game.GameService
import service.matrix.MatrixGenerator
import service.matrix.RandomMatrixGenerator
import java.math.BigDecimal


fun main(args: Array<String>) {
    val commandArgs = parseArguments(args)
    val bettingAmount = getBettingAmount(commandArgs)
    val configPath = getConfigPath(commandArgs)

    val gameService: GameService = createGameService(configPath)

    val gameResult: GameResult = gameService.play(bettingAmount)

    printResult(gameResult)
}

private fun printResult(gameResult: GameResult) {
    val json = Json { prettyPrint = true }

    println(json.encodeToString(gameResult))
}

fun parseArguments(args: Array<String>): Map<String, String> {
    return args.asList()
        .windowed(2, 2, false)
        .associate { (key, value) -> key to value }
}

fun getBettingAmount(argsMap: Map<String, String>): BigDecimal {
    val betAmountStr = argsMap["--betting-amount"]
        ?: throw IllegalArgumentException("Error: --betting-amount is required.")

    return betAmountStr.toDoubleOrNull()?.toBigDecimal()
        ?: throw IllegalArgumentException("Error: --betting-amount must be a valid double.")
}

fun getConfigPath(argsMap: Map<String, String>): String {
    return argsMap["--config"]
        ?: throw IllegalArgumentException("Error: --config is required.")
}


private fun createGameService(configPath: String): GameService {
    val configProvider: GameConfigProvider = FileGameConfigProvider(configPath)
    val matrixGenerator: MatrixGenerator = RandomMatrixGenerator()
    val combinationFinder: CombinationFinder = AllCombinationFinder(
        listOf(
            SameSymbolsCombinationFinder(),
            LinearSymbolsCombinationFinder()
        )
    )

    val rewardCalculator: RewardCalculator = DefaultRewardCalculator()

    return DefaultGameService(
        configProvider,
        matrixGenerator,
        combinationFinder,
        rewardCalculator
    )
}





