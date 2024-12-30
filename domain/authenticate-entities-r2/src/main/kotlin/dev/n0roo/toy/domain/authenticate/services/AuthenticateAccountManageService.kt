package dev.n0roo.toy.domain.authenticate.services

import dev.n0roo.toy.components.common.enums.AppTypes
import dev.n0roo.toy.components.common.exceptions.codes.ErrorMsgTypes
import dev.n0roo.toy.domain.authenticate.entities.AuthenticateAccount
import dev.n0roo.toy.domain.authenticate.repository.AuthenticateAccountRepository
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class AuthenticateAccountManageService (
    private val authenticateAccountRepository: AuthenticateAccountRepository
){

    suspend fun registeredAccount(
        userId: Long,
        serviceResources: AppTypes.Service.Resources,
        status: AppTypes.Authenticate.Status? = null
    ): AuthenticateAccount {
        return authenticateAccountRepository.save(
            AuthenticateAccount().apply {
                this.referenceUserId = userId
                this.serviceResources = serviceResources
                this.status = status?: AppTypes.Authenticate.Status.ON
            }
        )
    }

    suspend fun setAuthenticateAccountStatus(
        userId: Long,
        serviceResources: AppTypes.Service.Resources,
        status: AppTypes.Authenticate.Status
    ): AuthenticateAccount {
        fetchAuthenticateAccount(userId, serviceResources)?.let { account ->
            account.status = status
            if (status == AppTypes.Authenticate.Status.REMOVE) account.deleted = true
            account.issuedAt = ZonedDateTime.now()
            return authenticateAccountRepository.save(account)
        }
        throw ErrorMsgTypes.NotFound.NotMatchedUsers.throws
    }

    suspend fun fetchAuthenticateAccount(
        userId: Long,
        serviceResources: AppTypes.Service.Resources
    ): AuthenticateAccount? {
        return authenticateAccountRepository.findByReferenceUserIdAndServiceResourcesAndDeleted(
            userId = userId, serviceResources = serviceResources, deleted = false
        )
    }
}