package com.vibbra.timesheet.app.converter.auth

import com.vibbra.timesheet.app.entrypoint.rest.user.dto.request.CreateUserRequest
import com.vibbra.timesheet.domain.authentication.entity.Auth
import org.springframework.stereotype.Component

@Component
class AuthConverterImpl : AuthConverter {

    override fun toDomain(userRequest: CreateUserRequest): Auth {
        return Auth(
            login = userRequest.login,
            password = userRequest.password
        )
    }
}