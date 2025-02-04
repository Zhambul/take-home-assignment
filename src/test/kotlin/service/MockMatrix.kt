package service

import domain.Matrix



object MockMatrix {

    fun from(rows: List<List<String>>): Matrix {
        val bonus = rows.firstNotNullOf { row ->
            row.firstOrNull { cell ->
                cell.length > 1
            }
        }

        return Matrix(
            symbols = mutableMapOf<String, String>().apply {
                rows.forEachIndexed { rowIndex, row ->
                    row.forEachIndexed { colIndex, symbol ->
                        this["${rowIndex}:${colIndex}"] = symbol
                    }
                }
            },
            bonus = bonus
        )
    }
}