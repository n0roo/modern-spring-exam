package dev.n0roo.toy.components.common.exceptions.codes

data class ErrorCodes(
    val code: Int,
    val message: String,
) {
    companion object {
        fun of(code: Int, message: String): ErrorCodes {
            return ErrorCodes(code, message)
        }
    }
}
