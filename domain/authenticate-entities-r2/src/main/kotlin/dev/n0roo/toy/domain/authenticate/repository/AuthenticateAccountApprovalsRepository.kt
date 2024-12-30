package dev.n0roo.toy.domain.authenticate.repository

import dev.n0roo.toy.domain.authenticate.entities.AuthenticateAccountApprovals
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AuthenticateAccountApprovalsRepository: CoroutineCrudRepository<AuthenticateAccountApprovals, Long> {
}