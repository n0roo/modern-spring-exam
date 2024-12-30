package dev.n0roo.toy.components.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import javax.crypto.SecretKey

class JwtSupporter (
    private val keys: ByteArray,
){

    private val jwtKeys: SecretKey = Keys.hmacShaKeyFor(keys)

    fun generate(
        issuer: String,
        registrationId: String,
        userId: Long,
        expiredMillis: Long
    ): String {
        return Jwts.builder()
            .subject(userId.toString())
            .issuedAt(Date.from(Instant.now()))
            .expiration(Date.from(Instant.now().plus(expiredMillis, ChronoUnit.MILLIS)))
            .signWith(this.jwtKeys)
            .claims(
                mapOf(
                    "aud" to registrationId,
                    "iss" to issuer
                )
            )
            .compact()
    }


    fun getUserId(token: String): Long {
        return Jwts.parser()
            .verifyWith(jwtKeys).build()
            .parseSignedClaims(token)
            .payload
            .subject.toLong()
    }

    fun verifiedHeader(registrationId: String, token: String): Boolean {
        return Jwts.parser()
            .verifyWith(jwtKeys).build()
            .parseSignedClaims(token)
            .header.keyId == registrationId
    }

    fun isActivated(token: String): Boolean {
        return Jwts.parser()
            .verifyWith(jwtKeys).build()
            .parseSignedClaims(token)
            .payload.expiration.before(Date.from(Instant.now()))
    }

    fun getRegistrationId(token: String): String {
        return Jwts.parser()
            .verifyWith(jwtKeys).build()
            .parseSignedClaims(token)
            .payload
            .audience.first()
    }

    class Builder {
        private var key: String? = null

        fun config(key: String) = apply { this.key = key }
        fun build(): JwtSupporter {
            return JwtSupporter(key!!.toByteArray())
        }
    }
}