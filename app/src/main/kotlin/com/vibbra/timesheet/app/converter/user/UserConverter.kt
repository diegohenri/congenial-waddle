package com.vibbra.timesheet.app.converter.user

import com.vibbra.timesheet.app.entrypoint.rest.user.dto.request.CreateUserRequest
import com.vibbra.timesheet.app.entrypoint.rest.user.dto.response.UserResponse
import com.vibbra.timesheet.app.user.entity.UserEntity
import com.vibbra.timesheet.domain.user.model.User

interface UserConverter {

    fun toDomain(userRequest: CreateUserRequest): User
    fun toDomain(userEntity: UserEntity?): User?
    fun toResponse(userDomain: User?): UserResponse?
    fun toEntity(userDomain: User) : UserEntity
}