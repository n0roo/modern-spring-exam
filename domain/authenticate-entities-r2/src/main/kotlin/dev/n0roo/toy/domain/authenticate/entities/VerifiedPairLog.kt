package dev.n0roo.toy.domain.authenticate.entities

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.components.common.enums.AppTypes
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.ZonedDateTime

@Table(name = "verified_pair_log")
data class VerifiedPairLog (
    @Id
    @Column(value = "id")
    var id: Long? = null,
    @Column(value = "device_id")
    var deviceId: Long = 0,
    @Column(value = "resource_id")
    var resourceId: Long = 0,
    @Column(value = "pair_key")
    var pairKey: String = AppConsts.Delimiter.Blank,
    @Column(value = "pin_code")
    var pinCode: String = AppConsts.Delimiter.Blank,
    @Column(value = "type")
    var type: AppTypes.Authenticate.VerifiedType =  AppTypes.Authenticate.VerifiedType.EMAIL,
    @Column(value = "published_at")
    var publishedAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(value = "expired_at")
    var expiredAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(value = "verified")
    var verified: Boolean = false
)