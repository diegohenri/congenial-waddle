package com.vibbra.timesheet.app.entrypoint.rest.user.controller

import com.vibbra.timesheet.app.entrypoint.rest.user.dto.request.CreateUserRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/time-sheet/api/v1/users")
@RestController
class UserController {

    @PostMapping
    fun createUser(@RequestBody user : CreateUserRequest) {

    }
}