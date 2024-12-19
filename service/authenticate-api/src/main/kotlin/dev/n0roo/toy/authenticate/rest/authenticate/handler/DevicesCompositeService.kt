package dev.n0roo.toy.authenticate.rest.authenticate.handler

import dev.n0roo.toy.domain.common.cached.devices.models.DeviceInformation
import dev.n0roo.toy.domain.common.cached.devices.services.DevicesCacheManageService
import dev.n0roo.toy.authenticate.rest.authenticate.dto.DevicesDto
import dev.n0roo.toy.components.common.exceptions.codes.ErrorMsgTypes
import dev.n0roo.toy.domain.authenticate.entities.Devices
import dev.n0roo.toy.domain.authenticate.services.DevicesManageService
import org.springframework.stereotype.Service

@Service
class DevicesCompositeService
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
        storedDeviceWithCacheStore(storedDevice)
        return DevicesDto.CommandResponse.of(storedDevice)
    }

    suspend fun fetchDevices(registrationId: String): DevicesDto.CommandResponse {
        devicesCacheManageService.fetchDevices(registrationId)?.let {
            return DevicesDto.CommandResponse.of(it)
        }
        deviceManagedService.fetchDeviceWithRegistrationId(registrationId)?.let {
            storedDeviceWithCacheStore(it)
            return DevicesDto.CommandResponse.of(it)
        }
        throw ErrorMsgTypes.NotFound.RegistrationId.throws
    }

    suspend fun pathDevices(registrationId: String, appVersion: String, params: DevicesDto.UpdateRequest): DevicesDto.CommandResponse {
        val storedDevice = deviceManagedService.patchDeviceWithRegistrationId(
            registrationId = registrationId,
            notificationUID = params.notificationUID,
            appVersion = appVersion
        )
        storedDeviceWithCacheStore(storedDevice)
        return DevicesDto.CommandResponse.of(storedDevice)
    }


    private suspend fun storedDeviceWithCacheStore(storedDevice: Devices) {
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
    }
}