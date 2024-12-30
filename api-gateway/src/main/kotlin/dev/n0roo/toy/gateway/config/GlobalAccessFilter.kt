package dev.n0roo.toy.gateway.config

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component

@Component
class GlobalAccessFilter : AbstractGatewayFilterFactory<GlobalAccessFilter.Config>(Config::class.java) {

    private val logger = LoggerFactory.getLogger(GlobalAccessFilter::class.java)

    override fun apply(config: Config?): GatewayFilter {
        // grab configuration from Config object
        return GatewayFilter { exchange, chain ->
            val request: ServerHttpRequest = exchange.getRequest().mutate().build()
            chain.filter(exchange.mutate().request(request).build())
        }
    }

    class Config { //Put the configuration properties for your filter here
        lateinit var prefixDelimiter: String
        var logger: Boolean = false
    }
}