package dev.n0roo.toy.domain.common.cached.config.codec

import com.fasterxml.jackson.databind.ObjectMapper
import dev.n0roo.toy.components.common.exceptions.codes.ErrorMsgTypes
import kotlin.reflect.KClass

class JsonCodec (
    private val objectMapper: ObjectMapper
): Codec{
    override fun <T : Any> encode(data: T): String {
        require(data::class != Any::class) { "Data cannot be Any" }
        return runCatching { objectMapper.writeValueAsString(data) }
            .getOrElse {
                throw ErrorMsgTypes.JsonCodec.CanNotEncode.throws.apply {
                    this.message = "Cannot encode \"${data}\" to \"${String::class}\""
                }
            }
    }

    override fun <T : Any> decode(data: String, type: KClass<T>): T {
        return runCatching { objectMapper.readValue(data, type.java) }
            .getOrElse {
                throw ErrorMsgTypes.JsonCodec.CanNotDecode.throws.apply {
                    this.message = "Cannot decode \"${data}\" to \"${String::class}\""
                }
            }
    }

    override fun <T : Any> decode(data: String, type: TypeReference<T>): T {
        return runCatching {
            val javaType = objectMapper.typeFactory.constructType(type.type)
            objectMapper.readValue<T>(data, javaType)
        }.getOrElse {
            throw ErrorMsgTypes.JsonCodec.CanNotDecode.throws.apply {
                this.message = "Cannot decode \"${data}\" to \"${type.type}\""
            }
        }
    }
}