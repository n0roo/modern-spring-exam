package dev.n0roo.toy.domain.samples.entities.users.repository

import dev.n0roo.toy.components.common.enums.AppTypes
import dev.n0roo.toy.domain.samples.entities.users.entities.Users
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UsersRepository: JpaRepository<Users, Long> {

    fun findByEmailAndStatusIsIn(email: String, vararg status: AppTypes.Users.Status): Optional<Users>

    fun existsByEmailAndStatusIsIn(email: String, vararg status: AppTypes.Users.Status): Boolean

}