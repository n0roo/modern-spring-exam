package dev.n0roo.toy.authenticate.rest.authenticate.handler

import dev.n0roo.toy.domain.common.cached.devices.models.DeviceInformation
import dev.n0roo.toy.domain.common.cached.devices.services.DevicesCacheManageService
import dev.n0roo.toy.authenticate.rest.authenticate.dto.DevicesDto
import dev.n0roo.toy.domain.authenticate.services.DevicesManageService
import org.springframework.stereotype.Component

@Component
class DevicesCompositeHandler
constructor(
    private val deviceManagedService: DevicesManageService,
    private val devicesCacheManageService: DevicesCacheManageService
){

    suspend fun registrationDevices(appVersion: String, params: DevicesDto.RegistrationRequest): DevicesDto.CommandResponse {
        val storedDevice = deviceManagedService.registeredDevice(
            appVersion = appVersion,
            sourceDeviceId = params.deviceUUID,
            userAgent = params.userAgent,
            platformType = params.platformType,
            notificationUID = params.notificationUID
        )
        devicesCacheManageService.storedDevices(
            DeviceInformation(
                id = storedDevice.id!!,
                registrationId = storedDevice.registrationId,
                platformType = storedDevice.platformType,
                notificationUID = storedDevice.notificationUID,
                appVersion = storedDevice.appVersion,
                activated = storedDevice.activated,
                multipleConnected = storedDevice.multipleConnected
            )
        )
        return DevicesDto.CommandResponse.of(storedDevice)
    }
}