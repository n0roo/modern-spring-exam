package dev.n0roo.toy.domain.common.cached.config

import dev.n0roo.toy.domain.common.cached.config.properties.CacheProperties
import io.lettuce.core.ReadFrom
import io.lettuce.core.cluster.ClusterClientOptions
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.RedisClusterConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
class CacheConfig
constructor(
    private val cacheProperties: CacheProperties,
){

    @Profile("prod")
    @Bean
    fun reactiveRedisClusterConnectionFactory(): ReactiveRedisConnectionFactory {
        val clusterConfiguration = RedisClusterConfiguration()
        val topologyOptions = ClusterTopologyRefreshOptions.builder()
            .enableAdaptiveRefreshTrigger()
            .enablePeriodicRefresh(Duration.ofMinutes(20))
            .build()
        val clientOptions = ClusterClientOptions.builder()
            .topologyRefreshOptions(topologyOptions)
            .build()
        val clientConfiguration = LettuceClientConfiguration.builder()
            .commandTimeout(Duration.ofSeconds(15))
            .clientOptions(clientOptions)
            .readFrom(ReadFrom.REPLICA_PREFERRED)
            .build()
        return LettuceConnectionFactory(clusterConfiguration, clientConfiguration)
    }

    @Profile("dev")
    @Bean
    fun reactiveRedisConnectionFactory(): ReactiveRedisConnectionFactory {
        return LettuceConnectionFactory(cacheProperties.host, cacheProperties.port)
    }


    @Bean
    fun reactiveRedisOperations(): ReactiveRedisOperations<String, Any> {
        val serializer = Jackson2JsonRedisSerializer(Any::class.java)
        val contextBuilder = RedisSerializationContext.newSerializationContext<String, Any>(StringRedisSerializer())
        return ReactiveRedisTemplate(
            reactiveRedisConnectionFactory(),
            contextBuilder.value(serializer).hashValue(serializer).hashKey(serializer).build()
        )
    }

}