package dev.n0roo.toy.domain.common.cached.authenticate.services

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.domain.common.cached.authenticate.models.AuthenticateHash
import dev.n0roo.toy.domain.common.cached.components.CacheGenericOperator
import org.springframework.stereotype.Service

@Service
class AuthenticateHashManageService (
    private val cacheGenericOperator: CacheGenericOperator
){

    suspend fun storedAuthenticateHash(authenticate: AuthenticateHash, ttl: Long): AuthenticateHash {
        return cacheGenericOperator.setValue(AppConsts.Prefixed.Cache.Authorization.plus(authenticate.token), authenticate, ttl)
    }

    suspend fun fetchAuthenticateHash(token: String): AuthenticateHash? {
        return cacheGenericOperator.getValue(AppConsts.Prefixed.Cache.Authorization.plus(token), AuthenticateHash::class)
    }

    suspend fun expiredAuthenticateHash(token: String): AuthenticateHash? {
        return cacheGenericOperator.expiredValue(AppConsts.Prefixed.Cache.Authorization.plus(token), AuthenticateHash::class)
    }
}