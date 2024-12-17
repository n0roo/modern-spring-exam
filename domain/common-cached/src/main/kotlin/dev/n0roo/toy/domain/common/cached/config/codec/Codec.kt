package dev.n0roo.toy.domain.common.cached.config.codec

import kotlin.reflect.KClass

interface Codec {
    fun <T : Any> encode(data: T): String

    fun <T : Any> decode(data: String, type: KClass<T>): T

    fun <T : Any> decode(data: String, type: TypeReference<T>): T
}