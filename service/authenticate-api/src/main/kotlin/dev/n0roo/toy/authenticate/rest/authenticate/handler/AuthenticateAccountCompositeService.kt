package dev.n0roo.toy.authenticate.rest.authenticate.handler

import dev.n0roo.toy.authenticate.internal.messages.ExternalMessageBrokerService
import dev.n0roo.toy.authenticate.internal.messages.dto.SendVerifiedMessageDto
import dev.n0roo.toy.authenticate.rest.authenticate.dto.AuthenticateAccountDto
import dev.n0roo.toy.components.common.enums.AppTypes
import dev.n0roo.toy.components.common.exceptions.codes.ErrorMsgTypes
import dev.n0roo.toy.domain.authenticate.services.AuthenticateAccountManageService
import dev.n0roo.toy.domain.authenticate.services.AuthenticateAccountResourcesManageService
import dev.n0roo.toy.domain.authenticate.services.VerifiedPairLogManageService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class AuthenticateAccountCompositeService (
    private val authenticateAccountManageService: AuthenticateAccountManageService,
    private val authenticateAccountResourcesManageService: AuthenticateAccountResourcesManageService,
    private val verifiedPairLogManageService: VerifiedPairLogManageService,
    private val externalMessageBrokerService: ExternalMessageBrokerService
){

    suspend fun createUserAccountWithEmail(params: AuthenticateAccountDto.CreateUserAccountRequest) {
        if (existsResourceKeysWithApprovalResourceType(serviceResources = params.serviceResources, key = params.key, approvalResourceType = AppTypes.Authenticate.ApprovalResources.EMAIL) == AppTypes.Common.Exists.EXISTS) {
            throw ErrorMsgTypes.Conflict.AlreadyUsedKeyAndResourceType.throws
        }
        if (existsApprovalResourceTypeWithUsers(serviceResources = params.serviceResources, referenceUserId = params.userId, approvalResourceType = AppTypes.Authenticate.ApprovalResources.EMAIL) == AppTypes.Common.Exists.EXISTS) {
            throw ErrorMsgTypes.Conflict.AlreadyUsedResourceType.throws
        }
        val storedUsers = authenticateAccountManageService.registeredAccount(
            userId = params.userId,
            serviceResources = params.serviceResources
        )
        val storedResource = authenticateAccountResourcesManageService.registeredAccountResources(
            accountId = storedUsers.id!!,
            referenceUserId = storedUsers.referenceUserId,
            serviceResources = storedUsers.serviceResources,
            approvalResourceType = params.approvalResourceType,
            key = params.key
        )
        // @todo: send email verified
//        val verifiedPairLog = verifiedPairLogManageService.generatedPairLog()
        withContext(Dispatchers.IO) {
            externalMessageBrokerService.sendMails(SendVerifiedMessageDto(receiver = storedResource.key, pinCode = ""))
        }

    }

    suspend fun existsResourceKeysWithApprovalResourceType(serviceResources: AppTypes.Service.Resources, key: String, approvalResourceType: AppTypes.Authenticate.ApprovalResources): AppTypes.Common.Exists {
        return if (authenticateAccountResourcesManageService.existsResourceKeysWithApprovalResourceType(serviceResources, key, approvalResourceType)) AppTypes.Common.Exists.EXISTS
        else AppTypes.Common.Exists.NOT_EXISTS
    }

    suspend fun existsApprovalResourceTypeWithUsers(serviceResources: AppTypes.Service.Resources, referenceUserId: Long, approvalResourceType: AppTypes.Authenticate.ApprovalResources): AppTypes.Common.Exists {
        return if (authenticateAccountResourcesManageService.existsApprovalResourceTypeWithUsers(serviceResources, referenceUserId, approvalResourceType)) AppTypes.Common.Exists.EXISTS
        else AppTypes.Common.Exists.NOT_EXISTS
    }


}