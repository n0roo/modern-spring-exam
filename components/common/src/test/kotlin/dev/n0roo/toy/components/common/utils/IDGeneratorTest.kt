package dev.n0roo.toy.components.common.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("IDGenerator Unit Tests")
class IDGeneratorTest {

 @DisplayName("UUID Regex Check")
 @Test
 fun generateTimeBaseUUIDOnlyAlphabet() {
  val id = IDGenerator.generateTimeBaseUUIDOnlyAlphabet()
  assertTrue(id.length == 32)
  assertFalse(id.contains("-"))
 }

 @DisplayName("Unique Regex Check")
 @Test
 fun generateUnique() {
  val id = IDGenerator.generateUnique(6, "|")
  assertTrue(id.length == 39)
  assertTrue(id.contains("|"))
  assertTrue(id.split("|")[0].length == 6)
  assertTrue(id.split("|")[1].length == 32)
 }

 @DisplayName("Random Number Check")
 @Test
 fun generateRandomNumber() {
  val num = IDGenerator.generateRandomNumber(10)
  assertTrue(num.length == 10)
 }

 @DisplayName("Random String Check")
 @Test
 fun generateRandomCharacters() {
  val str = IDGenerator.generateRandomCharacters(10)
  assertTrue(str.length == 10)
 }
}