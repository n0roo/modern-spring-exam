package dev.n0roo.toy.domain.authenticate.repository

import dev.n0roo.toy.domain.authenticate.entities.VerifiedPairLog
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface VerifiedPairLogRepository: CoroutineCrudRepository<VerifiedPairLog, Long> {

    suspend fun existsByPairKeyAndVerified(pairKey: String, verified: Boolean): Boolean

    suspend fun findByPairKeyAndVerified(pairKey: String, verified: Boolean): VerifiedPairLog?
}