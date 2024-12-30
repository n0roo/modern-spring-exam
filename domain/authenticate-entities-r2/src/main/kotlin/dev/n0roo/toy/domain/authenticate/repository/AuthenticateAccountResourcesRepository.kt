package dev.n0roo.toy.domain.authenticate.repository

import dev.n0roo.toy.components.common.enums.AppTypes
import dev.n0roo.toy.domain.authenticate.entities.AuthenticateAccountResources
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AuthenticateAccountResourcesRepository: CoroutineCrudRepository<AuthenticateAccountResources, Long> {

    suspend fun existsByServiceResourcesAndKeyAndApprovalResourceTypeAndDeleted(serviceResources: AppTypes.Service.Resources, key: String, approvalResourceType: AppTypes.Authenticate.ApprovalResources, deleted: Boolean): Boolean

    suspend fun existsByReferenceUserIdAndServiceResourcesAndApprovalResourceTypeAndDeleted(userId: Long, serviceResources: AppTypes.Service.Resources, approvalResourceType: AppTypes.Authenticate.ApprovalResources, deleted: Boolean): Boolean

    suspend fun findByServiceResourcesAndKeyAndApprovalResourceTypeAndDeleted(serviceResources: AppTypes.Service.Resources, key: String, approvalResourceType: AppTypes.Authenticate.ApprovalResources, deleted: Boolean): AuthenticateAccountResources?
}