package dev.n0roo.toy.domain.authenticate.services

import dev.n0roo.toy.components.common.enums.AppTypes
import dev.n0roo.toy.components.common.utils.IDGenerator
import dev.n0roo.toy.domain.authenticate.TestConfiguration
import dev.n0roo.toy.domain.authenticate.entities.Devices
import dev.n0roo.toy.domain.authenticate.repository.DevicesRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import java.time.ZonedDateTime
import kotlin.jvm.optionals.getOrNull

@ActiveProfiles("auth-entity-dev")
@TestPropertySource(locations = ["classpath:config/application-auth-entity-dev.yml"])
@ContextConfiguration(classes = [TestConfiguration::class])
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeviceManagedServiceTest
@Autowired constructor(
    private val devicesRepository: DevicesRepository
){
    val testRegistrationId = IDGenerator.generateUnique(6, "_")
    @BeforeEach
    fun setup () {
        val devices = devicesRepository.save(
            Devices().apply {
                this.sourceDeviceId = "test-source-device-id"
                this.userAgent = "test-user-agent"
                this.platformType = AppTypes.Common.PlatformType.ETC
                this.registrationId = testRegistrationId
            }
        )
    }

    @Test
    fun registeredDevice() {
        val devices = devicesRepository.save(
            Devices().apply {
                this.sourceDeviceId = "test-source-device-id"
                this.userAgent = "test-user-agent"
                this.platformType = AppTypes.Common.PlatformType.ETC
                this.registrationId = IDGenerator.generateUnique(6, "_")
            }
        )
        assertNotNull(devices.id)
    }

    @Test
    fun patchDeviceWithRegistrationId() {
        val stored = devicesRepository.findByRegistrationId(testRegistrationId).getOrNull()
        assertNotNull(stored)
        stored?.let {
            it.sourceDeviceId = "test-source-device-id-modified"
            it.issuedAt = ZonedDateTime.now()
            val modified =  devicesRepository.save(it)
            assertTrue(modified.sourceDeviceId == "test-source-device-id-modified")
        }
    }

    @Test
    fun deActivatedDeviceWithRegistrationId() {
        val stored = devicesRepository.findByRegistrationId(testRegistrationId).getOrNull()
        assertNotNull(stored)
        stored?.let {
            it.activated = false
            it.issuedAt = ZonedDateTime.now()
            val modified =  devicesRepository.save(it)
            assertTrue(!modified.activated)
        }
    }

    @Test
    fun fetchDeviceWithRegistrationId() {
        assertTrue(devicesRepository.findByRegistrationId(testRegistrationId).isPresent)
    }
}