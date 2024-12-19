package dev.n0roo.toy.domain.authenticate.entities

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.components.common.enums.AppTypes
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.ZonedDateTime

@Table(name = "devices")
data class Devices (
    @Id
    @Column(value = "id")
    var id: Long? = null,
    @Column(value = "source_device_id")
    var sourceDeviceId: String = AppConsts.Delimiter.Blank,
    @Column(value = "user_agent")
    var userAgent: String = AppConsts.Delimiter.Blank,
    @Column(value = "platform_type")
    var platformType: AppTypes.Common.PlatformType = AppTypes.Common.PlatformType.ETC,
    @Column(value = "registration_id")
    var registrationId: String = AppConsts.Delimiter.Blank,
    @Column(value = "notification_uid")
    var notificationUID: String = AppConsts.Delimiter.Blank,
    @Column(value = "app_version")
    var appVersion: String = AppConsts.Delimiter.Blank,
    @Column(value = "activated")
    var activated: Boolean = true,
    @Column(value = "multiple_connected")
    var multipleConnected: Boolean = false,
    @Column(value = "created_at")
    var createdAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(value = "issued_at")
    var issuedAt: ZonedDateTime = ZonedDateTime.now()
)