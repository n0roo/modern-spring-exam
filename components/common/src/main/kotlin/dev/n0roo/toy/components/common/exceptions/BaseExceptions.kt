package dev.n0roo.toy.components.common.exceptions

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import dev.n0roo.toy.components.common.enums.AppTypes

@JsonIgnoreProperties(value = ["cause", "stackTrace", "suppressed"])
open class BaseExceptions(statusCode: Int, errorCode: Int, message: String, traceCases: AppTypes.Log.TraceCases? = null ) : RuntimeException(message) {
    var statusCode: Int = statusCode
    var errorCode: Int = errorCode
    var traceCases: AppTypes.Log.TraceCases = traceCases?: AppTypes.Log.TraceCases.INFO
    override var message: String = message
}