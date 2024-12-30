package dev.n0roo.toy.gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
class CorsConfig: WebFluxConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/**")
            .allowedMethods("*")
    }


//    @Bean
//    fun corsConfigurationSource(): CorsConfigurationSource {
//        val configuration = CorsConfiguration()
//        configuration.allowedHeaders = listOf("authorization", "Content-Type", "Content-Length", "Authorization", "credential", "X-XSRF-TOKEN", "tid", "RequestSource", "r-uri")
//        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
//        configuration.addAllowedOrigin("http://localhost:8080")
//        configuration.allowCredentials = true
//        configuration.maxAge = 7200
//        return UrlBasedCorsConfigurationSource()
//            .apply {
//                this.registerCorsConfiguration("/**", configuration)
//            }
//
//    }
}