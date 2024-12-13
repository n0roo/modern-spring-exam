package dev.n0roo.toy.components.common.exceptions.codes

import dev.n0roo.toy.components.common.exceptions.cases.ConflictExceptions
import dev.n0roo.toy.components.common.exceptions.cases.NotFoundExceptions

object ErrorMsgTypes {
    enum class NotFound (val throws: NotFoundExceptions) {
        RegistrationId(NotFoundExceptions(4040, "Not Matched Registration ID")),
        NotMatchedUsers(NotFoundExceptions(4041, "Not Matched Users")),
        ;
    }

    enum class Conflict(val throws: ConflictExceptions) {
        AlreadyUsedKeyAndResourceType(ConflictExceptions(4090, "Already Used Key Or Approval Resources")),
        AlreadyUsedResourceType(ConflictExceptions(4091, "Already Used Signing Method")),
        ;

    }
}