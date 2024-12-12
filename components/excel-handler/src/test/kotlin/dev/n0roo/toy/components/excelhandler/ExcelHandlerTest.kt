package dev.n0roo.toy.components.excelhandler

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


class ExcelHandlerTest {

    val handler = ExcelHandler()

    @DisplayName("ExcelHandler Test")
    @Test
    fun dynamicReadTest () {
        val resourceName = "test.xlsx"
        val resourceDirectory: Path = Paths.get("src", "test", "resources")
        val file = File("$resourceDirectory/$resourceName")

        assertNotNull(file)

        val result = handler.readFile(
            fileStream = file.inputStream(),
            cellConstraint = ExcelConstraint().apply {
                this.offsetRow = 7
                this.footerRow = 1
                this.constraint = listOf(
                    CellConstraint(0, "id", isNumberOnly = true),
                    CellConstraint(1, "name"),
                    CellConstraint(2, "engName"),
                    CellConstraint(3, "branchType"),
                    CellConstraint(4, "branchValue"),
                    CellConstraint(5, "type"),
                    CellConstraint(6, "establishmentValue")
                )
            },
            outputClass = SampleResult::class.java
        )

        assertTrue(result.size < 423)
        assertFalse(result[0].id == 1)

    }

}