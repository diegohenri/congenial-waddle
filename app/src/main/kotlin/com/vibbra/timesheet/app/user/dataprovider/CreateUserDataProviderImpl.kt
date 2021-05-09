package com.vibbra.timesheet.app.user.dataprovider

import com.vibbra.timesheet.app.converter.user.UserConverter
import com.vibbra.timesheet.app.user.postgresql.repository.UserRepository
import com.vibbra.timesheet.domain.user.dataprovider.CreateUserDataProvider
import com.vibbra.timesheet.domain.user.model.User
import org.springframework.stereotype.Component

@Component
class CreateUserDataProviderImpl(
    private val userConverter: UserConverter,
    private val repository: UserRepository
) : CreateUserDataProvider {

    override fun create(user: User): User? {
        val entity = userConverter.toEntity(user)
        return repository.save(entity).let {
            userConverter.toDomain(it)
        }
    }
}