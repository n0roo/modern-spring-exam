package dev.n0roo.toy.authenticate.rest.authenticate.dto

import com.fasterxml.jackson.annotation.JsonInclude
import dev.n0roo.toy.components.common.constants.ValidMessages
import dev.n0roo.toy.components.common.enums.AppTypes
import dev.n0roo.toy.domain.authenticate.entities.Devices
import dev.n0roo.toy.domain.common.cached.devices.models.DeviceInformation
import jakarta.validation.constraints.Max
import org.jetbrains.annotations.NotNull

object DevicesDto {

    data class RegistrationRequest(
        @field:NotNull(value = "userAgent ${ValidMessages.IS_REQUIRED}")
        val userAgent: String,
        val deviceUUID: String? = null,
        @field:NotNull(value = "platformType ${ValidMessages.IS_REQUIRED}")
        val platformType: AppTypes.Common.PlatformType,
        val notificationUID: String? = null,
        @field:Max(10, message = "Device ID must be less than or equal to 10")
        val testInt: Int = 1
    )

    data class UpdateRequest(
        @field:NotNull(value = "notificationUID ${ValidMessages.IS_REQUIRED}")
        val notificationUID: String,
    )

    data class CommandResponse(
        val registrationId: String,
        val platformType: AppTypes.Common.PlatformType,
        @field:JsonInclude(JsonInclude.Include.NON_NULL)
        val notificationUID: String? = null,
    ) {
        companion object {
            fun of(devices: Devices): CommandResponse = CommandResponse(
                registrationId = devices.registrationId,
                platformType = devices.platformType,
                notificationUID = devices.notificationUID,
            )

            fun of(devices: DeviceInformation): CommandResponse = CommandResponse(
                registrationId = devices.registrationId,
                platformType = devices.platformType,
                notificationUID = devices.notificationUID.ifBlank { null },
            )
        }
    }
}
