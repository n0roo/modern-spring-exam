package dev.n0roo.toy.domain.authenticate.entities

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.components.common.enums.AppTypes
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.ZonedDateTime

@Entity
@Table(name = "authenticate_account_approvals")
@Comment(value = "인증 이력")
class AuthenticateAccountApprovals {

    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name = "id", nullable = false)
    @get:Comment(value = "인증 인가 이력 아이디")
    var id: Long? = null

    @get:Column(name = "resource_id", nullable = false)
    @get:Comment(value = "인증 인가 리소스 아이디")
    var resourceId: Long = 0

    @get:Column(name = "device_id", nullable = false)
    @get:Comment(value = "인증 인가 기기 아이디")
    var deviceId: Long = 0

    @get:Column(name = "account_id", nullable = false)
    @get:Comment(value = "인증 사용자 등록 아이디")
    var accountId: Long = 0

    @get:Column(name = "reference_user_id", nullable = false)
    @get:Comment(value = "서비스 실제 사용자 아이디")
    var referenceUserId: Long = 0

    @get:Column(name = "token", nullable = false)
    @get:Comment(value = "억세스 토큰")
    var token: String = AppConsts.Delimiter.Blank

    @get:Column(name = "refresh_token", nullable = false)
    @get:Comment(value = "리프레시 토큰")
    var refreshToken: String = AppConsts.Delimiter.Blank

    @get:Column(name = "token_type", nullable = false)
    @get:Comment(value = "토큰 타입")
    @get:Enumerated(EnumType.STRING)
    var tokenType: AppTypes.Authenticate.TokenType = AppTypes.Authenticate.TokenType.BEARER

    @get:Column(name = "created_at", nullable = false)
    @get:Comment(value = "생성일")
    var createdAt: ZonedDateTime = ZonedDateTime.now()

    @get:Column(name = "expired_at", nullable = false)
    @get:Comment(value = "만료일/삭제일")
    var expiredAt: ZonedDateTime = ZonedDateTime.now()

    @get:Column(name = "deleted", nullable = false)
    @get:Comment(value = "삭제 폐기 여부")
    var deleted: Boolean = false
}