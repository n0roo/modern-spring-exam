package dev.n0roo.toy.domain.authenticate.services

import dev.n0roo.toy.domain.authenticate.entities.Devices
import dev.n0roo.toy.domain.authenticate.repository.DevicesRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DataJpaTest
class DeviceManagedServiceTest
constructor(
    private val devicesRepository: DevicesRepository
){

    @Test
    fun registeredDevice() {
        devicesRepository.save(
            Devices().apply {

            }
        )
    }

    @Test
    fun patchDeviceWithRegistrationId() {}

    @Test
    fun deActivatedDeviceWithRegistrationId() {}

    @Test
    fun fetchDeviceWithRegistrationId() {}
}