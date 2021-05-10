package com.vibbra.timesheet.app.user.entrypoint.rest.dto.response

import java.time.LocalDateTime

data class UserResponse(
    val id: String? = null,
    val name: String? = null,
    val email: String? = null,
    var login: String? = null,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?
)
