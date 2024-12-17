package dev.n0roo.toy.domain.common.cached.config.codec

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class TypeReference<T : Any> {
    val type: Type

    init {
        val superClass: Type = this.javaClass.genericSuperclass
        type = (superClass as ParameterizedType).actualTypeArguments[0]
    }
}