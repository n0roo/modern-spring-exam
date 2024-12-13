package dev.n0roo.toy.domain.authenticate.repository

import dev.n0roo.toy.components.common.enums.AppTypes
import dev.n0roo.toy.domain.authenticate.entities.AuthenticateAccount
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface AuthenticateAccountRepository: JpaRepository<AuthenticateAccount, Long> {

    fun findByReferenceUserIdAndServiceResourcesAndDeleted(userId: Long, serviceResources: AppTypes.Service.Resources, deleted: Boolean): Optional<AuthenticateAccount>
}