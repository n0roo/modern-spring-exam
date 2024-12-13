package dev.n0roo.toy.domain.authenticate.repository

import dev.n0roo.toy.components.common.enums.AppTypes
import dev.n0roo.toy.domain.authenticate.entities.AuthenticateAccountResources
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface AuthenticateAccountResourcesRepository: JpaRepository<AuthenticateAccountResources, Long> {

    fun existsByServiceResourcesAndKeyAndApprovalResourceTypeAndDeleted(serviceResources: AppTypes.Service.Resources, key: String, approvalResourceType: AppTypes.Authenticate.ApprovalResources, deleted: Boolean): Boolean

    fun existsByReferenceUserIdAndServiceResourcesAndApprovalResourceTypeAndDeleted(userId: Long, serviceResources: AppTypes.Service.Resources, approvalResourceType: AppTypes.Authenticate.ApprovalResources, deleted: Boolean): Boolean

    fun findByServiceResourcesAndKeyAndApprovalResourceTypeAndDeleted(serviceResources: AppTypes.Service.Resources, key: String, approvalResourceType: AppTypes.Authenticate.ApprovalResources, deleted: Boolean): Optional<AuthenticateAccountResources>
}