package dev.n0roo.toy.components.excelhandler

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.apache.poi.ss.usermodel.*
import org.apache.poi.util.IOUtils
import java.io.FileInputStream

class ExcelHandler (
    val cellArray: List<String> = listOf(
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
        "aa", "ab", "ac", "ad", "ae", "af", "ag", "ah", "ai", "aj", "ak", "al", "am", "an", "ao", "ap", "aq", "ar", "as", "at", "au", "av", "aw", "ax", "ay", "az",
        "ba", "bb", "bc", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bk", "bl", "bm", "bn", "bo", "bp", "bq", "br", "bs", "bt", "bu", "bv", "bw", "bx", "by", "bz",
        "ca"
    )
){

    fun read(fileStream: FileInputStream, cellRange: Int, offsetRow: Int = 1, limitRow: Int = 1, targetSheet: List<Int> = listOf()): List<CellData> {
        return readFile(fileStream, generatedConstraint(cellRange, offsetRow, limitRow, targetSheet), CellData::class.java)
    }

    private fun generatedConstraint(cellRange: Int, offsetRow: Int, limitRow: Int, targetSheet: List<Int>): ExcelConstraint {
        return ExcelConstraint().apply {
            this.offsetRow = offsetRow
            this.limitRow = limitRow
            this.targetSheet = targetSheet
            this.constraint =  cellArray.subList(0, cellRange)
                .mapIndexed { index, cellLabel ->
                    CellConstraint(index, cellLabel)
                }
        }
    }

    fun<T> readFile(fileStream: FileInputStream, cellConstraint: ExcelConstraint, outputClass: Class<T>): List<T> {

        IOUtils.setByteArrayMaxOverride(Int.MAX_VALUE)
        val workbook = WorkbookFactory.create(fileStream)

        if (cellConstraint.targetSheet.isNotEmpty()) {
            return cellConstraint.targetSheet.map {
                val sheet = workbook.getSheetAt(it)
                readSheet(workbook, sheet, cellConstraint, outputClass)
            }.flatMap { it }
        } else {
            val iterator = workbook.iterator()
            var result = listOf<T>()
            while (iterator.hasNext()) {
                val sheet = iterator.next()
                result += readSheet(workbook, sheet, cellConstraint, outputClass)
            }
            return result
        }

    }

    private fun<T> readSheet(workbook: Workbook, sheet: Sheet, cellConstraint: ExcelConstraint, outputClass: Class<T>):List<T> {
        val iterator = sheet.iterator()
        val objectMapper = ObjectMapper().registerKotlinModule()
        var result = listOf<T>()
        while (iterator.hasNext()) {
            val row = iterator.next()
            if (row.rowNum >= cellConstraint.offsetRow) {
                val cell = row.cellIterator()
                try {
                    result = result + listOf(objectMapper.convertValue(readCell(cell, cellConstraint), outputClass))
                } catch (e: Exception) {
                    e.printStackTrace()
                    println(readCell(cell, cellConstraint).toString())
                }
            }
        }
        return result
    }


    private fun readCell(iterator: Iterator<Cell>, cellConstraint: ExcelConstraint): Map<String, *> {
        val mutableMap = mutableMapOf<String, Any>()
        while (iterator.hasNext()) {
            val cell = iterator.next()
            cellConstraint.constraint.find { it.cellIdx == cell.address.column }?.let {
                when (cell.cellType) {
                    CellType.STRING -> {
                        mutableMap[it.outputKey] = if (it.isNumberOnly) {
                            cell.stringCellValue.replace(",","").replace("-","")
                        } else {
                            cell.stringCellValue
                        }
                    }
                    CellType.NUMERIC -> mutableMap[it.outputKey] = cell.numericCellValue
                    else -> mutableMap[it.outputKey] =  ""
                }
            }
        }
        return mutableMap
    }

    private fun readHeader(iterator: Iterator<Cell>, cellConstraint: ExcelConstraint): Map<String, *> {
        val mutableMap = mutableMapOf<String, Any>()

        while (iterator.hasNext()) {
            val cell = iterator.next()
            cellConstraint.constraint.find { it.cellIdx == cell.address.column }?.let {
                when (cell.cellType) {
                    CellType.STRING -> {
                        mutableMap[it.outputKey] = if (it.isNumberOnly) {
                            cell.stringCellValue.replace(",","").replace("-","")
                        } else {
                            cell.stringCellValue
                        }
                    }
                    CellType.NUMERIC -> mutableMap[it.outputKey] = cell.numericCellValue
                    else -> mutableMap[it.outputKey] =  ""
                }
            }
        }
        return mutableMap
    }
}
