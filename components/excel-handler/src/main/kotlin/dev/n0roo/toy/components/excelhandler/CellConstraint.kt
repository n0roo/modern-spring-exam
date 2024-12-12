package dev.n0roo.toy.components.excelhandler

data class CellConstraint(
    val cellIdx: Int,
    val outputKey: String,
    val isNumberOnly: Boolean = false,
    val hasRowRange: Boolean = false,
    val hierarchyLevel: Int? = null
)
