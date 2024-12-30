package dev.n0roo.toy.domain.authenticate.repository

import dev.n0roo.toy.components.common.enums.AppTypes
import dev.n0roo.toy.domain.authenticate.entities.AuthenticateAccount
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AuthenticateAccountRepository: CoroutineCrudRepository<AuthenticateAccount, Long> {
    suspend fun findByReferenceUserIdAndServiceResourcesAndDeleted(userId: Long, serviceResources: AppTypes.Service.Resources, deleted: Boolean): AuthenticateAccount?
}