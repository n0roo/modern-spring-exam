package dev.n0roo.toy.authenticate.rest.authenticate.dto

import com.fasterxml.jackson.annotation.JsonInclude
import dev.n0roo.toy.components.common.constants.ValidMessages
import dev.n0roo.toy.components.common.enums.AppTypes
import dev.n0roo.toy.domain.authenticate.entities.Devices
import org.jetbrains.annotations.NotNull
import java.time.ZonedDateTime

object DevicesDto {

    data class RegistrationRequest(
        @field:NotNull(value = "userAgent ${ValidMessages.IS_REQUIRED}")
        val userAgent: String,
        val deviceUUID: String? = null,
        @field:NotNull(value = "platformType ${ValidMessages.IS_REQUIRED}")
        val platformType: AppTypes.Common.PlatformType,
        val notificationUID: String? = null,
    )

    data class UpdateRequest(
        @field:NotNull(value = "notificationUID ${ValidMessages.IS_REQUIRED}")
        val notificationUID: String,
    )

    data class CommandResponse(
        val registrationId: String,
        val userAgent: String,
        val platformType: AppTypes.Common.PlatformType,
        @field:JsonInclude(JsonInclude.Include.NON_NULL)
        val deviceUUID: String? = null,
        @field:JsonInclude(JsonInclude.Include.NON_NULL)
        val notificationUID: String? = null,
        var createdAt: ZonedDateTime
    ) {
        companion object {
            fun of(devices: Devices): CommandResponse = CommandResponse(
                registrationId = devices.registrationId,
                userAgent = devices.userAgent,
                platformType = devices.platformType,
                deviceUUID = devices.sourceDeviceId,
                notificationUID = devices.notificationUID,
                createdAt = devices.createdAt
            )
        }
    }
}
