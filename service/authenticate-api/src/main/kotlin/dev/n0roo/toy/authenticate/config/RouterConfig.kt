package dev.n0roo.toy.authenticate.config

import dev.n0roo.toy.authenticate.rest.authenticate.DeviceManageHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfig {


    @Bean
    fun router(
        deviceManageHandler: DeviceManageHandler
    ) = coRouter {
        "/auth".nest {
            accept(MediaType.APPLICATION_JSON).nest {
                "/v1/devices".nest {
                    GET("/{registrationId}", deviceManageHandler::fetchDevices)
                    POST("", deviceManageHandler::registrationDevices)
                }
            }
        }
    }
}