package dev.n0roo.toy.components.jwt

data class JwtResult(
    val userId: Long,
    val registrationId: Long,
    val issuer: String,
    val userAuthorities: List<String>,
)
