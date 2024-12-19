package dev.n0roo.toy.authenticate.config.errors

import dev.n0roo.toy.components.common.exceptions.BaseExceptions
import dev.n0roo.toy.components.common.exceptions.ConditionalMessage
import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.reactive.function.server.*
import org.springframework.web.server.ServerWebInputException

@Component
class ExceptionHandler(
    errorAttributes: ApplicationErrorAttributes,
    resourceProperties: WebProperties.Resources,
    applicationContext: ApplicationContext,
    serverCodecConfigurer: ServerCodecConfigurer
): AbstractErrorWebExceptionHandler(
    errorAttributes,
    resourceProperties,
    applicationContext
) {

    init {
        super.setMessageWriters(serverCodecConfigurer.writers)
        super.setMessageReaders(serverCodecConfigurer.readers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes): RouterFunction<ServerResponse> {
        return RouterFunctions.route(
            RequestPredicates.all(),
            { request ->
                when (val throwable = errorAttributes.getError(request)){
                    is BaseExceptions -> {
                        ServerResponse.status(throwable.statusCode)
                            .bodyValue(ConditionalMessage(throwable.errorCode, throwable.message))
                    }
                    is WebExchangeBindException -> {
                        ServerResponse.status(HttpStatus.BAD_REQUEST)
                            .bodyValue(ConditionalMessage(4000, throwable.bindingResult.allErrors.joinToString("\n")))
                    }
                    is ServerWebInputException -> {
                        ServerResponse.status(HttpStatus.BAD_REQUEST)
                            .bodyValue(ConditionalMessage(4000, "Wrong Request Parameter"))
                    }
                    else -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .bodyValue(
                            ConditionalMessage(message = throwable.message?: throwable.cause?.message ?: "UnHandled Error")
                        )
                }


            }
        )
    }


}