package dev.n0roo.toy.domain.authenticate.entities

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.components.common.enums.AppTypes
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.ZonedDateTime

@Entity
@Table(name = "devices")
@Comment(value = "공통 기기 관리")
class Devices {

    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name ="id", nullable = false)
    @get:Comment(value = "기기 관리 아이디")
    var id: Long? = null

    @get:Column(name = "source_device_id", nullable = false)
    @get:Comment(value = "기기 UUID")
    var sourceDeviceId: String = AppConsts.Delimiter.Blank

    @get:Column(name = "user_agent", nullable = false)
    @get:Comment(value = "UserAgentString")
    var userAgent: String = AppConsts.Delimiter.Blank

    @get:Column(name = "platform_type", nullable = false)
    @get:Comment(value = "기기 플랫폼 유형")
    @get:Enumerated(EnumType.STRING)
    var platformType: AppTypes.Common.PlatformType = AppTypes.Common.PlatformType.ETC


    @get:Column(name = "registration_id", unique = true, nullable = false)
    @get:Comment(value = "기기 식별용 아이디/서버측 발행")
    var registrationId: String = AppConsts.Delimiter.Blank

    @get:Column(name = "notification_uid", nullable = false)
    @get:Comment(value = "fcm/apns 푸시아이디")
    var notificationUID: String = AppConsts.Delimiter.Blank

    @get:Column(name = "app_version", nullable = false)
    @get:Comment(value = "요청 앱버전")
    var appVersion: String = AppConsts.Delimiter.Blank

    @get:Column(name = "activated", nullable = false)
    @get:Comment(value = "활성여부")
    var activated: Boolean = true

    @get:Column(name = "multiple_connected", nullable = false)
    @get:Comment(value = "다중계정 접속 여부")
    var multipleConnected: Boolean = false

    @get:Column(name = "gps_allowed", nullable = false)
    @get:Comment(value = "gps 사용 허용여부")
    var gpsAllowed: Boolean = false

    @get:Column(name = "health_allowed", nullable = false)
    @get:Comment(value = "걸음수추적 사용 허용여부")
    var healthAllowed: Boolean = false

    @get:Column(name = "created_at", nullable = false)
    @get:Comment(value = "생성일")
    var createdAt: ZonedDateTime = ZonedDateTime.now()

    @get:Column(name = "issued_at", nullable = false)
    @get:Comment(value = "최종 수정일")
    var issuedAt: ZonedDateTime = ZonedDateTime.now()
}