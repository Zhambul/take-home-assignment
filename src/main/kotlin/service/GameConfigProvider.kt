package service

import config.GameConfig
import kotlinx.serialization.json.Json
import java.io.File

interface GameConfigProvider {

    fun get(): GameConfig
}

class FileGameConfigProvider(private val filePath: String) : GameConfigProvider {

    override fun get(): GameConfig {
        val file = File(filePath)
        val jsonContent = file.readText()
        return Json.decodeFromString<GameConfig>(jsonContent)
    }
}