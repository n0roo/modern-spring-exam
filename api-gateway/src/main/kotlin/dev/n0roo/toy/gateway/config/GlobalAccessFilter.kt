package dev.n0roo.toy.gateway.config

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.components.common.exceptions.codes.ErrorMsgTypes
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GlobalAccessFilter: AbstractGatewayFilterFactory<GlobalAccessFilter.Config>(Config::class.java) {

    private val logger = LoggerFactory.getLogger(GlobalAccessFilter::class.java)



    override fun apply(config: Config?): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request: ServerHttpRequest = exchange.request.mutate().build()
            val response = exchange.response
            val currentPath = request.uri.path
            val registrationId = request.headers.getFirst(AppConsts.HttpHeaders.RequestHeaderDeviceTokenKey)
            logger.info("Custom PRE filter : request id -> {}", request.id)
            if (valid(currentPath, registrationId)) {
                Mono.error(ErrorMsgTypes.Forbidden.ForbiddenError.throws)
            } else {
                chain.filter(exchange.mutate().request(request).build()).then(Mono.fromRunnable({
                    logger.info("Custom POST filter : response code -> {}", response.getStatusCode())
                }))
            }
        }
    }

    private fun valid(currentPath: String, registrationId: String?): Boolean {
        if (currentPath.matches(regex = "^\\/auth\\/v1\\/devices\\/+".toRegex())) {
            return true
        }
        if (registrationId.isNullOrBlank()) {
            return false
        }
        return false
    }

    class Config { //Put the configuration properties for your filter here
        lateinit var prefixDelimiter: String
        var logger: Boolean = false
    }
}