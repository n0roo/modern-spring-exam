package dev.n0roo.toy.gateway.config.security

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.components.common.enums.AppTypes
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


@Component
class SecurityContextRepository(
    private val globalAuthenticationManager: GlobalAuthenticationManager
): ServerSecurityContextRepository{


    override fun save(exchange: ServerWebExchange?, context: SecurityContext): Mono<Void> {
        throw UnsupportedOperationException("Not supported")
    }

    override fun load(serverWebExchange: ServerWebExchange): Mono<SecurityContext> {
        return Mono.just<ServerHttpRequest>(serverWebExchange.request)
            .mapNotNull<String> { serverHttpRequest: ServerHttpRequest ->
                serverHttpRequest.headers.getFirst(AppConsts.HttpHeaders.RequestHeaderAccessTokenKey)
            }
            .filter { authenticationHeader: String? ->
                authenticationHeader != null && authenticationHeader.startsWith(
                    ""
                )
            }
            .switchIfEmpty(Mono.empty())
            .map { authHeader: String -> authHeader.replace(AppConsts.Authenticate.Prefixed.TokenPrefix, "".trim { it <= ' ' }) }
            .flatMap { authToken ->
                globalAuthenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(authToken, authToken)
                )
            }
            .map{ SecurityContextImpl() }
    }
}