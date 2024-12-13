package dev.n0roo.toy.domain.authenticate.entities

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.components.common.enums.AppTypes
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.ZonedDateTime


@Entity
@Table(name = "authenticate_account_resource")
@Comment(value = "인증 이용 수단 / 로그인 수단")
class AuthenticateAccountResources {

    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name ="id", nullable = false)
    @get:Comment(value = "인증 사용자 등록 로그인 수단")
    var id: Long? = null

    @get:Column(name = "account_id", nullable = false)
    @get:Comment(value = "인증 사용자 등록 아이디")
    var accountId: Long = 0

    @get:Column(name = "reference_user_id", nullable = false)
    @get:Comment(value = "서비스 실제 사용자 아이디")
    var referenceUserId: Long = 0

    @get:Column(name = "service_resources", nullable = false)
    @get:Comment(value = "인증 수단 등록 서비스")
    @get:Enumerated(EnumType.STRING)
    var serviceResources: AppTypes.Service.Resources = AppTypes.Service.Resources.SYSTEM

    @get:Column(name = "key", nullable = false)
    @get:Comment(value = "아이디/이메일/소셜아이디/시그니쳐코드")
    var key: String = AppConsts.Delimiter.Blank

    @get:Column(name = "secret", nullable = false)
    @get:Comment(value = "비밀번호/pairkey")
    var secret: String = AppConsts.Delimiter.Blank

    @get:Column(name = "approval_resource_type", nullable = false)
    @get:Comment(value = "로그인 유형")
    @get:Enumerated(EnumType.STRING)
    var approvalResourceType: AppTypes.Authenticate.ApprovalResources = AppTypes.Authenticate.ApprovalResources.KV

    @get:Column(name = "status", nullable = false)
    @get:Comment(value = "로그인 유형 상태")
    @get:Enumerated(EnumType.STRING)
    var status: AppTypes.Authenticate.Status = AppTypes.Authenticate.Status.ON

    @get:Column(name = "issued_at", nullable = false)
    @get:Comment(value = "최종 수정일")
    var issuedAt: ZonedDateTime = ZonedDateTime.now()

    @get:Column(name = "created_at", nullable = false)
    @get:Comment(value = "생성일")
    var createAt: ZonedDateTime = ZonedDateTime.now()

    @get:Column(name = "deleted", nullable = false)
    @get:Comment(value = "삭제여부")
    var deleted: Boolean = false

    @get:Column(name = "error_reason", nullable = false)
    @get:Comment(value = "에러 상태. 사유")
    var errorReason: String = AppConsts.Delimiter.Blank
}