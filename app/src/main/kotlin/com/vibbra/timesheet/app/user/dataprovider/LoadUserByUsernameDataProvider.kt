package com.vibbra.timesheet.app.user.dataprovider

import com.vibbra.timesheet.app.user.entity.UserAuthenticationWrapper
import com.vibbra.timesheet.app.user.postgresql.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class LoadUserByUsernameDataProvider(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(login: String): UserDetails {
        return userRepository.findByLogin(login)?.let { UserAuthenticationWrapper(it) }
            ?: throw UsernameNotFoundException(login)
    }
}