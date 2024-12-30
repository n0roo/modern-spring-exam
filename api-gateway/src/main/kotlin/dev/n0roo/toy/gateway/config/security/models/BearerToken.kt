package dev.n0roo.toy.gateway.config.security.models

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils

class BearerToken(private val value: String): AbstractAuthenticationToken(AuthorityUtils.NO_AUTHORITIES) {

    override fun getCredentials() = value

    override fun getPrincipal() = value
}