package dev.n0roo.toy.domain.authenticate.entities

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.components.common.enums.AppTypes
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.ZonedDateTime

@Table(name = "authenticate_account_resource")
data class AuthenticateAccountResources(
    @Id
    @Column(value = "id")
    var id: Long? = null,
    @Column(value = "account_id")
    var accountId: Long = 0,
    @Column(value = "reference_user_id")
    var referenceUserId: Long = 0,
    @Column(value = "service_resources")
    var serviceResources: AppTypes.Service.Resources = AppTypes.Service.Resources.SYSTEM,
    @Column(value = "key")
    var key: String = AppConsts.Delimiter.Blank,
    @Column(value = "secret")
    var secret: String = AppConsts.Delimiter.Blank,
    @Column(value = "approval_resource_type")
    var approvalResourceType: AppTypes.Authenticate.ApprovalResources = AppTypes.Authenticate.ApprovalResources.KV,
    @Column(value = "status")
    var status: AppTypes.Authenticate.Status = AppTypes.Authenticate.Status.ON,
    @Column(value = "issued_at")
    var issuedAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(value = "created_at")
    var createAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(value = "deleted")
    var deleted: Boolean = false,
    @Column(value = "error_reason")
    var errorReason: String = AppConsts.Delimiter.Blank
)
