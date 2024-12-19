package dev.n0roo.toy.authenticate.config.errors

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class ResourcesConfigure {

    @Bean
    fun webResources(): WebProperties.Resources {
        return WebProperties.Resources()
    }
}