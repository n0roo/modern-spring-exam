package dev.n0roo.toy.authenticate.config.errors

import dev.n0roo.toy.components.common.exceptions.BaseExceptions
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest

@Component
class ApplicationErrorAttributes: DefaultErrorAttributes() {

    override fun getErrorAttributes(request: ServerRequest, options: ErrorAttributeOptions): MutableMap<String, Any> {

        val rewriteAttributes = super.getErrorAttributes(request, options)
        val throwable = getError(request)
        if (throwable is BaseExceptions) {
            val exceptions: BaseExceptions = getError(request) as BaseExceptions
            rewriteAttributes.put("exception", exceptions.javaClass.name)
            rewriteAttributes.put("message", exceptions.message)
            rewriteAttributes.put("status", exceptions.errorCode)
            rewriteAttributes.put("stackTrace", exceptions.stackTraceToString())
        }
        return super.getErrorAttributes(request, options)
    }
}