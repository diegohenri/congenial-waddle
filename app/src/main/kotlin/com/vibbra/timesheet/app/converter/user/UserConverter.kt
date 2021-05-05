package com.vibbra.timesheet.app.converter.user

import com.vibbra.timesheet.app.entrypoint.rest.user.dto.request.CreateUserRequest
import com.vibbra.timesheet.domain.user.entity.User

interface UserConverter {

    fun toDomain(userRequest: CreateUserRequest): User
}