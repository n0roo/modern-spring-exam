package dev.n0roo.toy.domain.authenticate.entities

import dev.n0roo.toy.components.common.enums.AppTypes
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.ZonedDateTime

@Table(name = "authenticate_account_approvals")
data class AuthenticateAccountApprovals(
    @Id
    @Column(value = "id")
    var id: Long? = null,
    @Column(value = "resource_id")
    var resourceId: Long,
    @Column(value = "device_id")
    var deviceId: Long,
    @Column(value = "account_id")
    var accountId: Long,
    @Column(value = "reference_user_id")
    var referenceUserId: Long,
    @Column(value = "token")
    var token: String,
    @Column(value = "refresh_token")
    var refreshToken: String,
    @Column(value = "token_type")
    var tokenType: AppTypes.Authenticate.TokenType = AppTypes.Authenticate.TokenType.BEARER,
    @Column(value = "created_at")
    var createdAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(value = "expired_at")
    var expiredAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(value = "deleted")
    var deleted: Boolean = false
)
