# Scratch Game Project

## Overview

This is a Kotlin-based implementation of a Scratch Game that generates a matrix of symbols and calculates rewards based on winning combinations and bonus symbols. 
The game follows a configuration-driven approach, allowing flexible game mechanics through a JSON configuration file.

## Project Features

- Configurable matrix size (rows and columns)
- Dynamic symbol probabilities
- Multiple winning combination types
- Bonus symbol mechanics
- Flexible reward calculation

## System Requirements

- JDK 1.8 or higher
- Gradle 6.8.3 or higher

## Configuration

The game uses a `config.json` file to define:
- Matrix dimensions
- Symbol types and probabilities
- Winning combinations
- Reward multipliers

## Building the Project

### Using Gradle

```bash
gradle clean build
```

## Running the Application

Execute the JAR file with required parameters:

```bash
java -jar build/libs/scratch-game.jar --config config.json --betting-amount 100
```

### Parameters

- `--config`: Path to the game configuration JSON file
- `--betting-amount`: Amount to bet in the game

## Running Tests

### Gradle

```bash
gradle test
```

## Game Mechanics

1. Generate a matrix of symbols based on configured probabilities
2. Identify winning combinations
3. Apply bonus symbols
4. Calculate final reward

### Winning Combinations

- Same symbols repeated
- Horizontal/vertical/diagonal line matches
- Configurable reward multipliers

### Bonus Symbols

Bonus symbols can:
- Multiply total reward
- Add extra reward
- Have no impact (MISS)

## Example Output

```json
{
    "matrix": [
        ["A", "A", "B"],
        ["A", "+1000", "B"],
        ["A", "A", "B"]
    ],
    "reward": 6600,
    "applied_winning_combinations": {
        "A": ["same_symbol_5_times", "same_symbols_vertically"],
        "B": ["same_symbol_3_times", "same_symbols_vertically"]
    },
    "applied_bonus_symbol": "+1000"
}
```