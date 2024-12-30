package dev.n0roo.toy.domain.common.cached.authenticate.models

import dev.n0roo.toy.components.common.enums.AppTypes
import java.io.Serializable
import java.time.ZonedDateTime

data class AuthenticateHash (
    val token: String,
    val userNo: Long,
    val expiredAt: ZonedDateTime,
    val authorities: List<AppTypes.Authenticate.AccountAuthorities>
) : Serializable