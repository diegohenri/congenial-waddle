package com.vibbra.timesheet.app.user.entrypoint.rest.dto.request

data class CreateUserRequest(
    var name: String,
    var email: String,
    var login: String,
    var password: String
)
