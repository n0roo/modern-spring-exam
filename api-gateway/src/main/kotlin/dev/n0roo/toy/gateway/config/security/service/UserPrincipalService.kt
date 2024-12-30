package dev.n0roo.toy.gateway.config.security.service

import dev.n0roo.toy.components.common.exceptions.codes.ErrorMsgTypes
import dev.n0roo.toy.domain.common.cached.authenticate.services.AuthenticateHashManageService
import dev.n0roo.toy.domain.common.cached.devices.services.DevicesCacheManageService
import dev.n0roo.toy.gateway.config.security.models.ApplicationUserDetails
import kotlinx.coroutines.reactor.mono
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserPrincipalService (
    private val authenticateHashManageService: AuthenticateHashManageService,
    private val devicesCacheManageService: DevicesCacheManageService

): ReactiveUserDetailsService {

    override fun findByUsername(username: String): Mono<UserDetails> {
        return mono {
            authenticateHashManageService.fetchAuthenticateHash(username).let {
                if (it == null) {
                    throw ErrorMsgTypes.UnAuthorized.UnAuthorizedToken.throws
                }
                ApplicationUserDetails(
                    userNo = it.userNo,
                    authorities = it.authorities,
                )
            }
        }
    }
}