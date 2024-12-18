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
    var sourceDeviceId: String = AppConsts.Delimiter.Blank,
    var userAgent: String = AppConsts.Delimiter.Blank,
    var platformType: AppTypes.Common.PlatformType = AppTypes.Common.PlatformType.ETC,
    var registrationId: String = AppConsts.Delimiter.Blank,
    var notificationUID: String = AppConsts.Delimiter.Blank,
    var appVersion: String = AppConsts.Delimiter.Blank,
    var activated: Boolean = true,
    var multipleConnected: Boolean = false,
    var createdAt: ZonedDateTime = ZonedDateTime.now(),
    var issuedAt: ZonedDateTime = ZonedDateTime.now()
)