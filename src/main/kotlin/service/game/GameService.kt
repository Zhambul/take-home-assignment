package service.game

import domain.GameResult
import java.math.BigDecimal

interface GameService {
    fun play(bettingAmount: BigDecimal): GameResult
}
