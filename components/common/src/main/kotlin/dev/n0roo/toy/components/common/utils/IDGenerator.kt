package dev.n0roo.toy.components.common.utils

import com.fasterxml.uuid.Generators
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class IDGenerator {
    companion object {
        private val charPool : List<Char> = ('a' .. 'z') + ('A'..'Z') + ('0'..'9')

        fun generateTimeBaseUUID(): UUID {
            return Generators.timeBasedGenerator().generate()
        }

        fun generateTimeBaseUUIDOnlyAlphabet(): String {
            return generateTimeBaseUUID().toString().replace("-", "")
        }

        fun generateUnique(fillIndex: Int, separator: String): String {
            return "${generateRandomCharacters(fillIndex)}$separator${generateTimeBaseUUIDOnlyAlphabet()}"
        }

        fun generateRandomNumber(fillIndex: Int): String {
            val sb = StringBuffer()
            for (i in 1..fillIndex) {
                sb.append((0 until 10).random().toString())
            }
            return sb.toString()
        }

        fun generateRandomCharacters(fillIndex: Int): String {
            return (1..fillIndex).map { kotlin.random.Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")
        }

        fun format(format: String, date: ZonedDateTime): String {
            val patter = DateTimeFormatter.ofPattern(format)
            return patter.format(date)
        }
    }
}