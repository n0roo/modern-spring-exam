package dev.n0roo.toy.components.common.exceptions.codes

import dev.n0roo.toy.components.common.exceptions.BaseExceptions
import dev.n0roo.toy.components.common.exceptions.cases.ConflictExceptions
import dev.n0roo.toy.components.common.exceptions.cases.JsonDecodedException
import dev.n0roo.toy.components.common.exceptions.cases.JsonEncodedException
import dev.n0roo.toy.components.common.exceptions.cases.NotFoundExceptions

object ErrorMsgTypes {
    enum class NotFound (val throws: NotFoundExceptions) {
        RegistrationId(NotFoundExceptions(4040, "Not Matched Registration ID")),
        NotMatchedUsers(NotFoundExceptions(4041, "Not Matched Users")),
        NotFoundData(NotFoundExceptions(4042, "Not Found Data")),
        ;
    }

    enum class Conflict(val throws: ConflictExceptions) {
        AlreadyUsedKeyAndResourceType(ConflictExceptions(4090, "Already Used Key Or Approval Resources")),
        AlreadyUsedResourceType(ConflictExceptions(4091, "Already Used Signing Method")),
        ;

    }

    enum class JsonCodec(val throws: BaseExceptions) {
        CanNotEncode(JsonEncodedException(5100, "Can not Encode Data")),
        CanNotDecode(JsonDecodedException(5101, "Can not Decode Data")),
        ;
    }
}