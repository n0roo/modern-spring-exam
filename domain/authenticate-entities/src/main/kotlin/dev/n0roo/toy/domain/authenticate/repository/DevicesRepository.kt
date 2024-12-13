package dev.n0roo.toy.domain.authenticate.repository

import dev.n0roo.toy.domain.authenticate.entities.Devices
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface DevicesRepository: JpaRepository<Devices, Long> {

    fun findByRegistrationId(registrationId: String): Optional<Devices>
}