package com.vibbra.timesheet.app.entrypoint.rest.user.dto.request

data class CreateUserRequest(
    var name: String,
    var email: String,
    var login: String,
    var password: String
)
