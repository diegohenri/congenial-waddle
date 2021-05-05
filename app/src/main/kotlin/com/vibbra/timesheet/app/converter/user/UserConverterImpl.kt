package com.vibbra.timesheet.app.converter.user

import com.vibbra.timesheet.app.converter.auth.AuthConverter
import com.vibbra.timesheet.app.entrypoint.rest.user.dto.request.CreateUserRequest
import com.vibbra.timesheet.domain.user.entity.User
import org.springframework.stereotype.Component

@Component
class UserConverterImpl(
    private val authConverter: AuthConverter
) : UserConverter {

    override fun toDomain(userRequest: CreateUserRequest): User {
        return User(
            name = userRequest.name,
            email = userRequest.email,
            auth = authConverter.toDomain(userRequest)
        )
    }
}