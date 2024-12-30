package dev.n0roo.toy.domain.authenticate.services

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.components.common.enums.AppTypes
import dev.n0roo.toy.components.common.exceptions.codes.ErrorMsgTypes
import dev.n0roo.toy.domain.authenticate.entities.AuthenticateAccountResources
import dev.n0roo.toy.domain.authenticate.repository.AuthenticateAccountResourcesRepository
import org.springframework.stereotype.Service

@Service
class AuthenticateAccountResourcesManageService(
    private val authenticateAccountResourcesRepository: AuthenticateAccountResourcesRepository
){

    //:todo approval resource type 유형별로 valid code 변경 필요
    suspend fun registeredAccountResources(
        accountId: Long,
        referenceUserId: Long,
        serviceResources: AppTypes.Service.Resources,
        key: String,
        secret: String? = null,
        signature: String? = null,
        approvalResourceType: AppTypes.Authenticate.ApprovalResources,
        status: AppTypes.Authenticate.Status? = null
    ): AuthenticateAccountResources {
        // key 와 인증 방식 중복여부
        if (existsResourceKeysWithApprovalResourceType(serviceResources, key, approvalResourceType)) {
            throw ErrorMsgTypes.Conflict.AlreadyUsedKeyAndResourceType.throws.apply {
                this.message = "Already Used $key in $approvalResourceType"
            }
        }
        // 사용자별 인증수단 중복여부
        if (existsApprovalResourceTypeWithUsers(serviceResources, referenceUserId, approvalResourceType)) {
            throw ErrorMsgTypes.Conflict.AlreadyUsedResourceType.throws.apply {
                this.message = "Already Used Signing Method $approvalResourceType"
            }
        }
        return authenticateAccountResourcesRepository.save(
            AuthenticateAccountResources().apply {
                this.accountId = accountId
                this.referenceUserId = referenceUserId
                this.serviceResources = serviceResources
                this.key = key
                this.secret = secret?: AppConsts.Delimiter.Blank
                this.approvalResourceType = approvalResourceType
                this.status = status?: AppTypes.Authenticate.Status.ON
            }
        )
    }

    suspend fun fetchAuthenticateResources(
        serviceResources: AppTypes.Service.Resources,
        key: String,
        approvalResourceType: AppTypes.Authenticate.ApprovalResources
    ): AuthenticateAccountResources? {
        return authenticateAccountResourcesRepository.findByServiceResourcesAndKeyAndApprovalResourceTypeAndDeleted(
            serviceResources = serviceResources, key = key, approvalResourceType = approvalResourceType, deleted = false
        )
    }

    suspend fun existsResourceKeysWithApprovalResourceType(serviceResources: AppTypes.Service.Resources, key: String, approvalResourceType: AppTypes.Authenticate.ApprovalResources): Boolean {
        return authenticateAccountResourcesRepository.existsByServiceResourcesAndKeyAndApprovalResourceTypeAndDeleted(
            serviceResources = serviceResources, key = key, approvalResourceType = approvalResourceType, deleted = false
        )
    }

    suspend fun existsApprovalResourceTypeWithUsers(serviceResources: AppTypes.Service.Resources, referenceUserId: Long, approvalResourceType: AppTypes.Authenticate.ApprovalResources): Boolean {
        return authenticateAccountResourcesRepository.existsByReferenceUserIdAndServiceResourcesAndApprovalResourceTypeAndDeleted(
            userId = referenceUserId, serviceResources = serviceResources, approvalResourceType = approvalResourceType, deleted = false
        )
    }
}