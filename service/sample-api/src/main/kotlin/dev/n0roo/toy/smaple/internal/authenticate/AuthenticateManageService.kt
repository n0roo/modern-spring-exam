package dev.n0roo.toy.smaple.internal.authenticate

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.components.common.enums.AppTypes
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "authenticate")
interface AuthenticateManageService {

    @GetMapping("/auth/approval-resources/exists", produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun existsAuthenticateResources(
        @RequestParam("approval-resource-type", required = true) approvalResourceType: AppTypes.Authenticate.ApprovalResources,
        @RequestParam("key", required = true) key: String,
    ): AppTypes.Common.Exists

    @PostMapping("/auth/account", produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun createUserAccount(
        @RequestHeader(AppConsts.HttpHeaders.RequestHeaderDeviceTokenKey) registrationId: String,
        @RequestBody params: Any
    )
}