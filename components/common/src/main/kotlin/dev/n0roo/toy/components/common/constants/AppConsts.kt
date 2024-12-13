package dev.n0roo.toy.components.common.constants

object AppConsts {

    object System {
        const val BasePackage = "dev.n0roo.toy"
    }

    object Authenticate {
        object Prefixed {
            const val FillCharSize = 5
        }
    }

    object Delimiter {
        const val Comma = ","
        const val Colon = ":"
        const val SemiColon = ";"
        const val Asterisk = "*"
        const val Blank = ""
        const val Dash = "-"
        const val UnderScore = "_"
        const val PadBar = " | "
        const val Dot = "."
        const val IsVal = "="
        const val Question = "?"
        const val LineBreak = "\n"
        const val LineBreakAt = "\n \t at"
    }

    object Prefixed {
        const val Uri = "uri"
        const val Request = "REQ"
        const val Response = "RES"
        const val Result = "result"
        const val Method = "method"
        const val ReturnCode = "rtnCode"
        const val ReturnMessage = "rtnMsg"
        const val ErrorCode = "errorCode"
        const val ErrorMessage = "errorMessages"
        const val Elapsed = "elapsed"
        const val Unknown = "unknown"
    }

    object HttpHeaders {
        const val ContentType = "Content-Type"
        const val ContentLength = "Content-Length"

        const val UserAgent = "User-Agent"
        const val RequestHeaderAccessTokenKey = "Authorization"
        const val RequestHeaderDeviceTokenKey = "Registration"
    }
}