package dev.n0roo.toy.domain.authenticate.repository

import dev.n0roo.toy.domain.authenticate.entities.Devices
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface DevicesRepository: CoroutineCrudRepository<Devices, Long> {
    fun findByRegistrationId(registrationId: String): Optional<Devices>
}