package com.vibbra.timesheet.domain.user.model

import java.time.LocalDateTime

data class User(
    var id: String? = null,
    var name: String,
    var email: String,
    val login: String,
    var password: String,
    var createdAt: LocalDateTime? = LocalDateTime.now(),
    var updatedAt: LocalDateTime? = LocalDateTime.now()
)