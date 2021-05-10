package com.vibbra.timesheet.app.user.converter

import com.vibbra.timesheet.app.user.entrypoint.rest.dto.request.CreateUserRequest
import com.vibbra.timesheet.app.user.entrypoint.rest.dto.response.UserResponse
import com.vibbra.timesheet.app.user.entity.UserEntity
import com.vibbra.timesheet.domain.user.model.User

interface UserConverter {

    fun toDomain(userRequest: CreateUserRequest): User
    fun toDomain(userEntity: UserEntity?): User?
    fun toResponse(userDomain: User?): UserResponse?
    fun toResponse(userEntity: UserEntity?): UserResponse?
    fun toEntity(userDomain: User) : UserEntity
}