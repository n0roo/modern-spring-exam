package dev.n0roo.toy.domain.authenticate.repository

import dev.n0roo.toy.domain.authenticate.entities.VerifiedPairLog
import org.springframework.data.jpa.repository.JpaRepository

interface VerifiedPairLogRepository: JpaRepository<VerifiedPairLog, Long> {
}