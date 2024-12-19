package dev.n0roo.toy.domain.authenticate.repository

import dev.n0roo.toy.domain.authenticate.entities.Devices
import org.springframework.data.repository.kotlin.CoroutineCrudRepository


interface DevicesRepository: CoroutineCrudRepository<Devices, Long> {
    suspend fun findTopByRegistrationIdAndActivated(registrationId: String, activated: Boolean): Devices?
}