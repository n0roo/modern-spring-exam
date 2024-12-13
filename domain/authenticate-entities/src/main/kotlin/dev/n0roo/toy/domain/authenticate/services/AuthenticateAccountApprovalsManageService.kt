package dev.n0roo.toy.domain.authenticate.services

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.components.common.enums.AppTypes
import dev.n0roo.toy.domain.authenticate.entities.AuthenticateAccountApprovals
import dev.n0roo.toy.domain.authenticate.entities.AuthenticateAccountResources
import dev.n0roo.toy.domain.authenticate.entities.Devices
import dev.n0roo.toy.domain.authenticate.repository.AuthenticateAccountApprovalsRepository
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class AuthenticateAccountApprovalsManageService
constructor(
    private val authenticateAccountApprovalsRepository: AuthenticateAccountApprovalsRepository
){

    fun registeredApprovals(
        resources: AuthenticateAccountResources,
        devices: Devices,
        token: String? = null,
        refreshToken: String,
        tokenType: AppTypes.Authenticate.TokenType,
        expiredHours: Long
    ): AuthenticateAccountApprovals {
        return authenticateAccountApprovalsRepository.save(
            AuthenticateAccountApprovals().apply {
                this.resourceId = resources.id!!
                this.deviceId = devices.id!!
                this.accountId = resources.accountId
                this.referenceUserId = resources.referenceUserId
                this.token = if (token.isNullOrBlank()) {
                    AppConsts.Delimiter.Blank
                } else {
                    token
                }
                this.refreshToken = refreshToken
                this.tokenType = tokenType
                this.expiredAt = ZonedDateTime.now().plusHours(expiredHours)
            }
        )
    }

}