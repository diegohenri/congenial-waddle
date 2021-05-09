package com.vibbra.timesheet.app.user.entrypoint.rest.controller

import com.vibbra.timesheet.app.user.converter.UserConverter
import com.vibbra.timesheet.app.user.entrypoint.rest.dto.request.CreateUserRequest
import com.vibbra.timesheet.app.user.entrypoint.rest.dto.response.UserResponse
import com.vibbra.timesheet.domain.user.usecase.CreateUserUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/users")
@RestController
class UserController(
    private val createUser: CreateUserUseCase,
    private val userConverter: UserConverter
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody userRequest: CreateUserRequest): UserResponse? {
        val user = userConverter.toDomain(userRequest)
        return createUser
            .create(user)
            .takeIf { it != null }?.let { userConverter.toResponse(it) }
    }
}