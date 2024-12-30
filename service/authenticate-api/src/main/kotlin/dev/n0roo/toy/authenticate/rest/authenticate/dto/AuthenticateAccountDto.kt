package dev.n0roo.toy.authenticate.rest.authenticate.dto

import dev.n0roo.toy.components.common.enums.AppTypes
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

object AuthenticateAccountDto {

    data class CreateUserAccountRequest(
        @field:NotNull
        val userId: Long,
        @field:NotNull
        val serviceResources: AppTypes.Service.Resources,
        val status: AppTypes.Authenticate.Status? = null,
        @field:NotEmpty
        val key: String,

        val approvalResourceType: AppTypes.Authenticate.ApprovalResources
    )
}