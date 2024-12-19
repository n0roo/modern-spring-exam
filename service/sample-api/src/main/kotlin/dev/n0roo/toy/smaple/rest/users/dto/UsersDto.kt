package dev.n0roo.toy.smaple.rest.users.dto

import dev.n0roo.toy.components.common.constants.ValidMessages
import org.jetbrains.annotations.NotNull

object UsersDto {
    data class CreateUserRequest(
        @field:NotNull(value = "email ${ValidMessages.IS_REQUIRED}")
        val email: String,
        @field:NotNull(value = "name ${ValidMessages.IS_REQUIRED}")
        val name: String
    )
}