package dev.n0roo.toy.domain.common.cached.components

import dev.n0roo.toy.domain.common.cached.config.codec.JsonCodec
import dev.n0roo.toy.domain.common.cached.config.codec.TypeReference
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.Duration
import kotlin.reflect.KClass

@Component
class CacheGenericOperator
constructor(
    private val reactiveRedisOperations: ReactiveRedisOperations<String, String>,
    private val jsonCodec: JsonCodec
){

    suspend fun <T : Any> getValue(key: String, clazz: KClass<T>): T? {
        return reactiveRedisOperations.opsForValue()
            .get(key)
            .map { jsonCodec.decode(it, clazz) }
            .switchIfEmpty(Mono.empty())
            .awaitSingleOrNull()
    }

    suspend fun <T: Any> setValue(key: String, source: T): T {
        return reactiveRedisOperations.opsForValue()
            .set(key, jsonCodec.encode(source))
            .map { source }.awaitSingle()
    }

    suspend fun <T: Any> expiredValue(key: String, clazz: KClass<T>): T? {
        return reactiveRedisOperations.opsForValue()
            .getAndExpire(key, Duration.ZERO)
            .map { jsonCodec.decode(it, clazz) }
            .switchIfEmpty(Mono.empty())
            .awaitSingleOrNull()
    }


}