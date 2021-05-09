package com.vibbra.timesheet.app.user.dataprovider

import com.vibbra.timesheet.app.user.converter.UserConverter
import com.vibbra.timesheet.app.user.postgresql.repository.UserRepository
import com.vibbra.timesheet.domain.user.dataprovider.FindUserDataProvider
import com.vibbra.timesheet.domain.user.model.User
import org.springframework.stereotype.Component

@Component
class FindUserDataProviderImpl(
    private val repository: UserRepository,
    private val userConverter: UserConverter
) : FindUserDataProvider {

    override fun findByEmailOrLogin(email: String, login: String): User? {
        return repository
            .findFirstByEmailOrLogin(email, login)
            ?.let {
                userConverter.toDomain(it)
            }
    }
}