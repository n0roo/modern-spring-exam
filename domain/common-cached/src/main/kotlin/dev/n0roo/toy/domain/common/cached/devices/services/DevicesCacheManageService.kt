package dev.n0roo.toy.domain.common.cached.devices.services

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.domain.common.cached.components.CacheGenericOperator
import dev.n0roo.toy.domain.common.cached.devices.models.DeviceInformation
import org.springframework.stereotype.Service

@Service
class DevicesCacheManageService
constructor(
    private val cacheGenericOperator: CacheGenericOperator
){

    suspend fun storedDevices(devices: DeviceInformation): DeviceInformation {
        return cacheGenericOperator.setValue(AppConsts.Prefixed.Cache.RegistrationId.plus(devices.registrationId), devices)
    }

    suspend fun fetchDevices(registrationId: String): DeviceInformation? {
        return cacheGenericOperator.getValue(AppConsts.Prefixed.Cache.RegistrationId.plus(registrationId), DeviceInformation::class)
    }

    suspend fun expiredDevices(registrationId: String): DeviceInformation? {
        return cacheGenericOperator.expiredValue(AppConsts.Prefixed.Cache.RegistrationId.plus(registrationId), DeviceInformation::class)
    }
}