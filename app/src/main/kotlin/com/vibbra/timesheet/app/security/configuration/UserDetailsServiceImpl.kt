package com.vibbra.timesheet.app.security.configuration

import com.vibbra.timesheet.app.user.postgresql.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(login: String): UserDetails {
        return userRepository.findByLogin(login)?.let { UserDetailsImpl(it) } ?: throw UsernameNotFoundException(login)
    }
}