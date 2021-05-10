package com.vibbra.timesheet.app.user.entrypoint.rest.dto.response

import java.time.LocalDateTime

data class UserResponse(
    val id: String?,
    val name: String,
    val email: String,
    var login: String,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?
)
