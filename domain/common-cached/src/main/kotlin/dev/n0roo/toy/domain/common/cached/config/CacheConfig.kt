package dev.n0roo.toy.domain.common.cached.config

import dev.n0roo.toy.domain.common.cached.config.codec.JsonCodec
import dev.n0roo.toy.domain.common.cached.config.properties.CacheProperties
import io.lettuce.core.ReadFrom
import io.lettuce.core.cluster.ClusterClientOptions
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.RedisClusterConfiguration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Primary

@Configuration
class CacheConfig
constructor(
    private val cacheProperties: CacheProperties,
){

    @Profile("common-cache-prod")
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

    @Primary
    @Profile("common-cache-dev")
    @Bean
    fun reactiveRedisConnectionFactory(): ReactiveRedisConnectionFactory {
        return LettuceConnectionFactory(
            RedisStandaloneConfiguration(cacheProperties.host, cacheProperties.port)
        )
    }


    @Bean
    fun reactiveRedisOperations(): ReactiveRedisOperations<String, Any> {
        val serializer = Jackson2JsonRedisSerializer(Any::class.java)
        val stringSerializer = StringRedisSerializer()
        val contextBuilder = RedisSerializationContext.newSerializationContext<String, Any>(StringRedisSerializer())
        return ReactiveRedisTemplate(
            reactiveRedisConnectionFactory(),
            contextBuilder.value(serializer)
                .hashKey(stringSerializer)
                .hashValue(serializer)
                .build()
        )
    }

    @Bean
    fun jsonCodec(): JsonCodec {
        return JsonCodec(jacksonObjectMapper())
    }

}