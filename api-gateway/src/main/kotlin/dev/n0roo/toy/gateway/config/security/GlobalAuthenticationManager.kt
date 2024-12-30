package dev.n0roo.toy.gateway.config.security

import dev.n0roo.toy.components.common.exceptions.codes.ErrorMsgTypes
import dev.n0roo.toy.gateway.config.security.models.BearerToken
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.mono
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GlobalAuthenticationManager(
    private val userPrincipalService: ReactiveUserDetailsService
): ReactiveAuthenticationManager{

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        return Mono.justOrEmpty(authentication)
            .filter { auth -> auth is BearerToken }
            .cast(BearerToken::class.java)
            .flatMap { token ->  mono{validateToken(token)} }
            .onErrorMap { ErrorMsgTypes.UnAuthorized.UnAuthorizedToken.throws.apply { this.message += "\n ${it.message}" } }
    }

    private suspend fun validateToken(token: BearerToken): Authentication {
        val userDetails = userPrincipalService.findByUsername(token.principal).awaitSingle()
        return UsernamePasswordAuthenticationToken(userDetails, userDetails.password, userDetails.authorities)
    }
}