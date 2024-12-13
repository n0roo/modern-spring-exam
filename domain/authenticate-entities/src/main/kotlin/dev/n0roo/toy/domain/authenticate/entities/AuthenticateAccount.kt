package dev.n0roo.toy.domain.authenticate.entities

import dev.n0roo.toy.components.common.enums.AppTypes
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.time.ZonedDateTime

@Entity
@Table(name = "authenticate_account")
@Comment(value = "인증 등록 유저")
class AuthenticateAccount {

    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name = "id")
    @get:Comment(value = "인증 인가 등록 아이디")
    var id: Long? = null

    @get:Column(name = "reference_user_id")
    @get:Comment(value = "서비스 실제 사용자 아이디")
    var referenceUserId: Long = 0

    @get:Column(name = "service_resources")
    @get:Comment(value = "인증 수단 등록 서비스")
    @get:Enumerated(EnumType.STRING)
    var serviceResources: AppTypes.Service.Resources = AppTypes.Service.Resources.SYSTEM

    @get:Column(name = "status")
    @get:Comment(value = "인증 상태")
    @get:Enumerated(EnumType.STRING)
    var status: AppTypes.Authenticate.Status = AppTypes.Authenticate.Status.ON

    @get:Column(name = "created_at")
    @get:Comment(value= "생성일")
    var createdAt: ZonedDateTime = ZonedDateTime.now()

    @get:Column(name = "issued_at")
    @get:Comment(value = "최종 수정일")
    var issuedAt: ZonedDateTime = ZonedDateTime.now()

    @get:Column(name = "deleted")
    @get:Comment(value = "삭제여부")
    var deleted: Boolean = false
}