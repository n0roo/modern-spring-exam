package dev.n0roo.toy.domain.authenticate.entities

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.components.common.enums.AppTypes
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.ZonedDateTime

@Entity
@Table(name = "verified_pair_log")
class VerifiedPairLog {

    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name = "id")
    @get:Comment("인증키 로그")
    var id: Long? = null

    @get:Column(name = "device_id")
    @get:Comment("기기 아이디")
    var deviceId: Long = 0

    @get:Column(name = "resource_id")
    @get:Comment("인증 리소스 아이디")
    var resourceId: Long = 0

    @get:Column(name = "pair_key")
    @get:Comment("페어키")
    var pairKey: String = AppConsts.Delimiter.Blank

    @get:Column(name = "pin_code")
    @get:Comment("pin code")
    var pinCode: String = AppConsts.Delimiter.Blank

    @get:Column(name = "type")
    @get:Comment("인증 타입")
    @get:Enumerated(EnumType.STRING)
    var type: AppTypes.Authenticate.VerifiedType =  AppTypes.Authenticate.VerifiedType.EMAIL

    @get:Column(name = "published_at")
    @get:Comment("인증키 게시일")
    var publishedAt: ZonedDateTime = ZonedDateTime.now()

    @get:Column(name = "expired_at")
    @get:Comment("인증만료일")
    var expiredAt: ZonedDateTime = ZonedDateTime.now()

    @get:Column(name = "verified")
    @get:Comment("인증여부")
    var verified: Boolean = false

}