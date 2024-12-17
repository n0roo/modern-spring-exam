package dev.n0roo.toy.service.authenticate.rest.authenticate.services

import dev.n0roo.toy.domain.authenticate.services.DeviceManagedService
import dev.n0roo.toy.domain.common.cached.devices.models.DeviceInformation
import dev.n0roo.toy.domain.common.cached.devices.services.DevicesCacheManageService
import dev.n0roo.toy.service.authenticate.rest.authenticate.dto.DevicesDto
import org.springframework.stereotype.Service

@Service
class DevicesCompositeService
constructor(
    private val deviceManagedService: DeviceManagedService,
    private val devicesCacheManageService: DevicesCacheManageService
){

    fun registrationDevices(appVersion: String, params: DevicesDto.RegistrationRequest): DevicesDto.CommandResponse {
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