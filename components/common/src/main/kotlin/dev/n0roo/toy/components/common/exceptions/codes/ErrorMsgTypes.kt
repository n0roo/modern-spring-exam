package dev.n0roo.toy.components.common.exceptions.codes

import dev.n0roo.toy.components.common.exceptions.BaseExceptions
import dev.n0roo.toy.components.common.exceptions.cases.*

object ErrorMsgTypes {
    enum class UnAuthorized (val throws: UnAuthorizedExceptions) {
        UnAuthorizedToken(UnAuthorizedExceptions(4010, "UnAuthorized Request")),
        ;
    }

    enum class NotFound (val throws: NotFoundExceptions) {
        RegistrationId(NotFoundExceptions(4040, "Not Matched Registration ID")),
        NotMatchedUsers(NotFoundExceptions(4041, "Not Matched Users")),
        NotFoundData(NotFoundExceptions(4042, "Not Found Data")),
        ;
    }

    enum class NotAcceptable (val throws: NotAcceptableExceptions) {
        Expired(NotAcceptableExceptions(4060, "Not Acceptable. Already Expired")),
        ;
    }

    enum class Conflict(val throws: ConflictExceptions) {
        AlreadyUsedKeyAndResourceType(ConflictExceptions(4090, "Already Used Key Or Approval Resources")),
        AlreadyUsedResourceType(ConflictExceptions(4091, "Already Used Signing Method")),
        AlreadyUsedEmailAddress(ConflictExceptions(4092, "Already Used Email Address")),
        ;

    }

    enum class JsonCodec(val throws: BaseExceptions) {
        CanNotEncode(JsonEncodedException(5100, "Can not Encode Data")),
        CanNotDecode(JsonDecodedException(5101, "Can not Decode Data")),
        ;
    }
}