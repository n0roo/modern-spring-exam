package dev.n0roo.toy.domain.authenticate.services

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.components.common.enums.AppTypes
import dev.n0roo.toy.components.common.exceptions.codes.ErrorMsgTypes
import dev.n0roo.toy.components.common.utils.IDGenerator
import dev.n0roo.toy.domain.authenticate.entities.Devices
import dev.n0roo.toy.domain.authenticate.repository.DevicesRepository
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import kotlin.jvm.optionals.getOrNull

@Service
class DevicesManageService
constructor(
    private val devicesRepository: DevicesRepository
){

    suspend fun registeredDevice(
        sourceDeviceId: String? = null,
        userAgent: String? = null,
        platformType: AppTypes.Common.PlatformType,
        notificationUID: String? = null,
        appVersion: String,
    ): Devices {
        return devicesRepository.save(
            Devices().apply {
                sourceDeviceId?.let { this.sourceDeviceId = it }
                userAgent?.let { this.userAgent = it }
                this.platformType = platformType
                this.registrationId = IDGenerator.generateUnique(
                    fillIndex = AppConsts.Authenticate.Prefixed.FillCharSize,
                    separator = AppConsts.Delimiter.UnderScore
                )
                notificationUID?.let { this.notificationUID = it }
                this.appVersion = appVersion
            }
        )
    }

    suspend fun patchDeviceWithRegistrationId(
        registrationId: String,
        notificationUID: String? = null,
        appVersion: String,
    ): Devices {
        fetchDeviceWithRegistrationId(registrationId)?.let { devices ->
            devices.appVersion = appVersion
            devices.activated = true
            notificationUID?.let { devices.notificationUID = it }
            devices.issuedAt = ZonedDateTime.now()
            return devicesRepository.save(devices)
        }
        throw ErrorMsgTypes.NotFound.RegistrationId.throws
    }

    suspend fun deActivatedDeviceWithRegistrationId(registrationId: String): Devices {
        fetchDeviceWithRegistrationId(registrationId)?.let { devices ->
            devices.activated = false
            devices.issuedAt = ZonedDateTime.now()
            return devicesRepository.save(devices)
        }
        throw ErrorMsgTypes.NotFound.RegistrationId.throws
    }

    fun fetchDeviceWithRegistrationId(registrationId: String): Devices? {
        return devicesRepository.findByRegistrationId(registrationId).getOrNull()
    }
}