package dev.n0roo.toy.domain.authenticate.repository

import dev.n0roo.toy.domain.authenticate.entities.AuthenticateAccountApprovals
import org.springframework.data.jpa.repository.JpaRepository

interface AuthenticateAccountApprovalsRepository: JpaRepository<AuthenticateAccountApprovals, Long> {
}