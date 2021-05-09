package com.vibbra.timesheet.app.security.response

import com.vibbra.timesheet.app.entrypoint.rest.user.dto.response.UserResponse

data class AuthenticationResponse(
    val token: String,
    val user: UserResponse
)
