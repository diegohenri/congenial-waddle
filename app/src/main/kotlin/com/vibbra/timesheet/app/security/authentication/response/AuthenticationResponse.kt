package com.vibbra.timesheet.app.security.authentication.response

import com.vibbra.timesheet.app.user.entrypoint.rest.dto.response.UserResponse

data class AuthenticationResponse(
    val token: String,
    val user: UserResponse?
)
