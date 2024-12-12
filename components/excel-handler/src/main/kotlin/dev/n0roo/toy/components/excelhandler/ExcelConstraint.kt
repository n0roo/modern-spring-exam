package dev.n0roo.toy.components.excelhandler

class ExcelConstraint {

    var offsetRow: Int = 0
    var limitRow: Int? = null
    var targetSheet: List<Int> = listOf()
    var footerRow: Int? = null
    var footerCell: Int? = null
    var footerKey: String? = null
    var offsetCell: Int? = null
    var limitCell: Int? = null
    var constraint: List<CellConstraint> = listOf()
}