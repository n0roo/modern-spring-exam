package dev.n0roo.toy.smaple.rest.users.services

import dev.n0roo.toy.components.common.enums.AppTypes
import dev.n0roo.toy.domain.samples.entities.users.services.UserManageService
import dev.n0roo.toy.smaple.internal.authenticate.AuthenticateManageService
import dev.n0roo.toy.smaple.rest.users.dto.UsersDto
import org.springframework.stereotype.Service

@Service
class UsersCompositeService (
    private val userManageService: UserManageService,
    private val authenticateManageService: AuthenticateManageService
){

    fun existsByEmail(email: String): AppTypes.Common.Exists {
        return if (userManageService.existsByEmail(email)) AppTypes.Common.Exists.EXISTS
        else AppTypes.Common.Exists.NOT_EXISTS
    }

    fun createUser(params: UsersDto.CreateUserRequest) {
        val storedUsers = userManageService.createUsers(
            email = params.email,
            name = params.name,
        )
    }
}