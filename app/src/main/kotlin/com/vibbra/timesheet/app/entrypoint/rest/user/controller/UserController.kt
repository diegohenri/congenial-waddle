package com.vibbra.timesheet.app.entrypoint.rest.user.controller

import com.vibbra.timesheet.app.converter.user.UserConverter
import com.vibbra.timesheet.app.entrypoint.rest.user.dto.request.CreateUserRequest
import com.vibbra.timesheet.domain.user.usecase.CreateUserUseCaseImpl
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/time-sheet/api/v1/users")
@RestController
class UserController(
    private val createUser: CreateUserUseCaseImpl,
    private val userConverter: UserConverter
) {

    @PostMapping
    fun createUser(@RequestBody userRequest: CreateUserRequest) {
        val user = userConverter.toDomain(userRequest)
        createUser.create(user)
    }
}