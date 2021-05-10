package com.vibbra.timesheet.app.user.entrypoint.rest.controller

import com.vibbra.timesheet.app.user.converter.UserConverter
import com.vibbra.timesheet.app.user.entity.UserAuthenticationWrapper
import com.vibbra.timesheet.app.user.entrypoint.rest.dto.request.CreateUserRequest
import com.vibbra.timesheet.app.user.entrypoint.rest.dto.request.UpdateUserRequest
import com.vibbra.timesheet.app.user.entrypoint.rest.dto.response.UserResponse
import com.vibbra.timesheet.domain.user.usecase.CreateUserUseCase
import com.vibbra.timesheet.domain.user.usecase.UpdateUserUseCase
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/v1/users")
@RestController
class UserController(
    private val createUser: CreateUserUseCase,
    private val userConverter: UserConverter,
    private val updateUser: UpdateUserUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody userRequest: CreateUserRequest): UserResponse? {
        val user = userConverter.toDomain(userRequest)
        return createUser
            .create(user)
            ?.let { userConverter.toResponse(it) }
    }

    @PutMapping
    fun updateUser(@RequestBody userRequest: UpdateUserRequest, @AuthenticationPrincipal authenticatedUser: UserAuthenticationWrapper): UserResponse? {
        val actualUser = userConverter.toDomain(authenticatedUser.user)
        val userInformation = userConverter.toDomain(actualUser, userRequest)

        return updateUser
            .update(actualUser = actualUser, newUserInformation = userInformation)
            ?.let { userConverter.toResponse(it) }
    }

    @GetMapping
    fun getUserInfo(@AuthenticationPrincipal authenticatedUser: UserAuthenticationWrapper): UserResponse? {
        return authenticatedUser.user.let { userConverter.toResponse(it) }
    }
}