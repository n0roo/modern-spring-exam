package dev.n0roo.toy.domain.common.cached.devices.models

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.components.common.enums.AppTypes
import java.io.Serializable

data class DeviceInformation(
    val id: Long,
    val registrationId: String,
    val platformType: AppTypes.Common.PlatformType = AppTypes.Common.PlatformType.ETC,
    var notificationUID: String = AppConsts.Delimiter.Blank,
    var appVersion: String = AppConsts.Delimiter.Blank,
    var activated: Boolean = true,
    var multipleConnected: Boolean = false,
): Serializable