package dev.n0roo.toy.domain.samples.entities.users.entities

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.components.common.enums.AppTypes
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.ZonedDateTime

@Comment("사용자 관리 테이블")
@Table(name = "users")
@Entity
class Users {

    @get:Comment("사용자 관리 아이디")
    @get:Column(name = "id", nullable = false, unique = true)
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Id
    var id: Long? = null

    @get:Comment("사용자 이메일")
    @get:Column(name = "email", nullable = false, unique = true)
    var email: String = AppConsts.Delimiter.Blank

    @get:Comment("사용자 명")
    @get:Column(name = "name", nullable = false, unique = true)
    var name: String = AppConsts.Delimiter.Blank

    @get:Comment("사용자 상태")
    @get:Column(name = "status", nullable = false, unique = true)
    @get:Enumerated(EnumType.STRING)
    var status: AppTypes.Users.Status = AppTypes.Users.Status.ON

    @get:Comment("생성일")
    @get:Column(name = "created_at", nullable = false, unique = true)
    var createdAt: ZonedDateTime = ZonedDateTime.now()

    @get:Comment("수정일")
    @get:Column(name = "issued_at", nullable = false, unique = true)
    var issuedAt: ZonedDateTime = ZonedDateTime.now()
}