package com.vibbra.timesheet.app.user.dataprovider

import com.vibbra.timesheet.app.user.converter.UserConverter
import com.vibbra.timesheet.app.user.postgresql.repository.UserRepository
import com.vibbra.timesheet.domain.user.dataprovider.UpdateUserDataProvider
import com.vibbra.timesheet.domain.user.model.User
import org.springframework.stereotype.Component

@Component
class UpdateUserDataProviderImpl(
    private val userRepository: UserRepository,
    private val userConverter: UserConverter
) : UpdateUserDataProvider {

    override fun update(user: User): User {
        return userRepository
            .save(userConverter.toEntity(user))
            .let { userConverter.toDomain(it) }
    }
}