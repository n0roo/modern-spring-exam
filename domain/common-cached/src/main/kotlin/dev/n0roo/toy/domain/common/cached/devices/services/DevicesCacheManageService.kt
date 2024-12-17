package dev.n0roo.toy.domain.common.cached.devices.services

import dev.n0roo.toy.domain.common.cached.components.CacheGenericOperator
import dev.n0roo.toy.domain.common.cached.devices.models.DeviceInformation
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class DevicesCacheManageService
constructor(
    private val cacheGenericOperator: CacheGenericOperator
){

    fun storedDevices(devices: DeviceInformation): Mono<DeviceInformation> {
        return cacheGenericOperator.setValue(devices.registrationId, devices)
    }

    fun fetchDevices(registrationId: String): Mono<DeviceInformation> {
        return cacheGenericOperator.getValue(registrationId, DeviceInformation::class)
    }

    fun expiredDevices(registrationId: String): Mono<DeviceInformation> {
        return cacheGenericOperator.expiredValue(registrationId, DeviceInformation::class)
    }
}