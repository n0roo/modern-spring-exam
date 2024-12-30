package dev.n0roo.toy.gateway.config.security

import dev.n0roo.toy.components.jwt.JwtSupporter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.ServerSecurityContextRepository

@Configuration
@EnableWebFluxSecurity
class SecurityConfig (
    private val globalAuthenticationManager: GlobalAuthenticationManager,
    private val securityContextRepository: ServerSecurityContextRepository,
){

    @Bean
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .formLogin { it.disable() }
            .authenticationManager(globalAuthenticationManager)
            .securityContextRepository(securityContextRepository)
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
            .authorizeExchange { exchanges ->
                exchanges.pathMatchers(
                    "/auth/v1/devices/**"
                ).permitAll()
                    .anyExchange().authenticated()
            }
            .build()
    }


    @Bean
    fun jwtSupporter(): JwtSupporter {
        return JwtSupporter.Builder()
            .config("123456789012345678901234567890123456").build()
    }
}