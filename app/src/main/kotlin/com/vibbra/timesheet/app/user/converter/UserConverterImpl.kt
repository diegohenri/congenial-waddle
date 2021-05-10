package com.vibbra.timesheet.app.user.converter

import com.vibbra.timesheet.app.user.entity.UserEntity
import com.vibbra.timesheet.app.user.entrypoint.rest.dto.request.CreateUserRequest
import com.vibbra.timesheet.app.user.entrypoint.rest.dto.request.UpdateUserRequest
import com.vibbra.timesheet.app.user.entrypoint.rest.dto.response.UserResponse
import com.vibbra.timesheet.domain.user.model.User
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UserConverterImpl : UserConverter {

    override fun toDomain(userRequest: CreateUserRequest): User {
        return User(
            name = userRequest.name,
            email = userRequest.email,
            login = userRequest.login,
            password = userRequest.password
        )
    }

    override fun toResponse(userDomain: User?): UserResponse? {
        return userDomain?.let {
            UserResponse(
                id = it.id,
                name = it.name,
                email = it.email,
                login = it.login,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }
    }

    override fun toResponse(userEntity: UserEntity?): UserResponse? {
        return userEntity?.let {
            UserResponse(
                id = it.id,
                name = it.name,
                email = it.email,
                login = it.login,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }
    }

    override fun toDomain(userEntity: UserEntity): User {
        return User(
            id = userEntity.id,
            name = userEntity.name,
            email = userEntity.email,
            login = userEntity.login,
            password = userEntity.password,
            createdAt = userEntity.createdAt,
            updatedAt = userEntity.updatedAt
        )
    }

    override fun toEntity(userDomain: User): UserEntity {
        return userDomain.let {
            UserEntity(
                id = it.id!!,
                name = it.name,
                email = it.email,
                login = it.login,
                password = it.password,
                createdAt = it.createdAt.takeIf { date -> date != null } ?: LocalDateTime.now(),
                updatedAt = it.updatedAt.takeIf { date -> date != null } ?: LocalDateTime.now(),
            )
        }
    }

    override fun toDomain(actualUser: User, userRequest: UpdateUserRequest): User {
        return User(
            name = userRequest.name,
            email = userRequest.email,
            login = actualUser.login,
            password = actualUser.password
        )
    }
}