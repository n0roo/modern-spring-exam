package dev.n0roo.toy.authenticate.config.errors

import dev.n0roo.toy.components.common.exceptions.cases.BadRequestExceptions
import org.springframework.stereotype.Component
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Validator
import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName

@Component
class FunctionalValidator(
    private val validator: Validator
){

    fun <T: Any> validate(params: T, clazz: KClass<*>): Boolean {
        val error = BeanPropertyBindingResult(params, clazz::class.jvmName)
        validator.validate(params, error)
        if (error.allErrors.isNotEmpty()) {
            throw BadRequestExceptions(4000, error.fieldError?.defaultMessage.orEmpty())
        }
        return true
    }
}