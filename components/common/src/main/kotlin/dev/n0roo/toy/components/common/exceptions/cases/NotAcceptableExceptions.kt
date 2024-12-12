package dev.n0roo.toy.components.common.exceptions.cases

import dev.n0roo.toy.components.common.enums.AppTypes
import dev.n0roo.toy.components.common.exceptions.BaseExceptions

class NotAcceptableExceptions (errorCode: Int, message: String, traceCases: AppTypes.Log.TraceCases? = null):
    BaseExceptions(statusCode = 500, errorCode, message, traceCases)