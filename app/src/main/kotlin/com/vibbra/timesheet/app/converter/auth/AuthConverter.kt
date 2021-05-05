package com.vibbra.timesheet.app.converter.auth

import com.vibbra.timesheet.app.entrypoint.rest.user.dto.request.CreateUserRequest
import com.vibbra.timesheet.domain.authentication.entity.Auth

interface AuthConverter {

    fun toDomain(userRequest: CreateUserRequest): Auth
}
