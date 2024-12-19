package dev.n0roo.toy.domain.samples.entities.users.services

import dev.n0roo.toy.components.common.enums.AppTypes
import dev.n0roo.toy.components.common.exceptions.codes.ErrorMsgTypes
import dev.n0roo.toy.domain.samples.entities.users.entities.Users
import dev.n0roo.toy.domain.samples.entities.users.repository.UsersRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class UserManageService (
    private val usersRepository: UsersRepository,
){

    fun createUsers(
        email: String,
        name: String,
        status: AppTypes.Users.Status = AppTypes.Users.Status.ON
    ): Users {
        if (existsByEmail(email)) {
            throw ErrorMsgTypes.Conflict.AlreadyUsedEmailAddress.throws.apply { this.message += "[$email]" }
        }
        return usersRepository.save(
            Users().apply {
                this.email = email
                this.name = name
                this.status = status
            }
        )
    }

    fun existsByEmail(email: String): Boolean {
        return usersRepository.existsByEmailAndStatusIsIn(email, AppTypes.Users.Status.ON, AppTypes.Users.Status.OK, AppTypes.Users.Status.BLOCK)
    }

    fun findByEmail(email: String): Users? {
        return usersRepository.findByEmailAndStatusIsIn(email, AppTypes.Users.Status.ON, AppTypes.Users.Status.OK, AppTypes.Users.Status.BLOCK).getOrNull()
    }
}