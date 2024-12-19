package dev.n0roo.toy.domain.authenticate.entities

import dev.n0roo.toy.components.common.enums.AppTypes
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.ZonedDateTime

@Table(name = "authenticate_account")
data class AuthenticateAccount(
    @Id
    @Column(value = "id")
    var id: Long? = null,
    @Column(value = "reference_user_id")
    var referenceUserId: Long,
    @Column(value = "service_resources")
    var serviceResources: AppTypes.Service.Resources = AppTypes.Service.Resources.SYSTEM,
    @Column(value = "status")
    var status: AppTypes.Authenticate.Status = AppTypes.Authenticate.Status.ON,
    @Column(value = "created_at")
    var createdAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(value = "issued_at")
    var issuedAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(value = "deleted")
    var deleted: Boolean = false
)
