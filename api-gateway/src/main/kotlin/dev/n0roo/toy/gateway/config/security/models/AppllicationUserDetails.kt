package dev.n0roo.toy.gateway.config.security.models

import dev.n0roo.toy.components.common.constants.AppConsts
import dev.n0roo.toy.components.common.enums.AppTypes
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class ApplicationUserDetails(
    val userNo: Long,
    val authorities: List<AppTypes.Authenticate.AccountAuthorities>
): UserDetails {


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities.map { SimpleGrantedAuthority(it.name) }.toMutableSet()
    }

    override fun getPassword() = AppConsts.Delimiter.Blank

    override fun getUsername() = AppConsts.Delimiter.Blank
}