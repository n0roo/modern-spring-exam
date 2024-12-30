package dev.n0roo.toy.domain.authenticate.entities

import dev.n0roo.toy.components.common.constants.AppConsts
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
    var resourceId: Long = 0,
    @Column(value = "device_id")
    var deviceId: Long = 0,
    @Column(value = "account_id")
    var accountId: Long = 0,
    @Column(value = "reference_user_id")
    var referenceUserId: Long = 0,
    @Column(value = "token")
    var token: String = AppConsts.Delimiter.Blank,
    @Column(value = "refresh_token")
    var refreshToken: String = AppConsts.Delimiter.Blank,
    @Column(value = "token_type")
    var tokenType: AppTypes.Authenticate.TokenType = AppTypes.Authenticate.TokenType.BEARER,
    @Column(value = "created_at")
    var createdAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(value = "expired_at")
    var expiredAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(value = "deleted")
    var deleted: Boolean = false
)
