package dev.n0roo.toy.gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .authorizeExchange { authorize ->
                authorize.anyExchange().permitAll()
            }
            .httpBasic(withDefaults())
            .cors {}
            .build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedHeaders = listOf("authorization", "Content-Type", "Content-Length", "Authorization", "credential", "X-XSRF-TOKEN", "tid", "RequestSource", "r-uri")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
        configuration.addAllowedOrigin("http://localhost:8080")
        configuration.allowCredentials = true
        configuration.maxAge = 7200
        return UrlBasedCorsConfigurationSource()
            .apply {
                this.registerCorsConfiguration("/**", configuration)
            }

    }
}