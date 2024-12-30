package dev.n0roo.toy.domain.authenticate.services

import dev.n0roo.toy.components.common.enums.AppTypes
import dev.n0roo.toy.components.common.exceptions.codes.ErrorMsgTypes
import dev.n0roo.toy.components.common.utils.IDGenerator
import dev.n0roo.toy.domain.authenticate.entities.VerifiedPairLog
import dev.n0roo.toy.domain.authenticate.repository.VerifiedPairLogRepository
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class VerifiedPairLogManageService (
    private val verifiedPairLogRepository: VerifiedPairLogRepository
){

    suspend fun generatedPairLog(
        deviceId: Long,
        resourceId: Long,
        type: AppTypes.Authenticate.VerifiedType,
        expiredAfterMin: Long,
    ): VerifiedPairLog {
        return verifiedPairLogRepository.save(
            VerifiedPairLog().apply {
                this.deviceId = deviceId
                this.resourceId = resourceId
                this.pairKey = IDGenerator.generateUnique(10, "_")
                this.pinCode = IDGenerator.generateRandomNumber(6)
                this.type = type
                this.expiredAt = ZonedDateTime.now().plusMinutes(expiredAfterMin)
            }
        )
    }

    suspend fun existsPairLogByPairKey(pairKey: String): Boolean {
        return verifiedPairLogRepository.existsByPairKeyAndVerified(pairKey, false)
    }

    suspend fun fetchPairLogByPairKey(pairKey: String): VerifiedPairLog {
        verifiedPairLogRepository.findByPairKeyAndVerified(pairKey, false)?.let {
            if (it.expiredAt.compareTo(ZonedDateTime.now()) == 0) {
                throw ErrorMsgTypes.NotAcceptable.Expired.throws
            }
            return it
        }
        throw ErrorMsgTypes.NotFound.NotFoundData.throws
    }

    suspend fun verifiedLogs(pairLogId: Long): VerifiedPairLog {
        verifiedPairLogRepository.findById(pairLogId)?.let {
            it.verified = true
            it.expiredAt = ZonedDateTime.now()
            return verifiedPairLogRepository.save(it)
        }
        throw ErrorMsgTypes.NotFound.NotFoundData.throws
    }
}