package com.vibbra.timesheet.domain.user.entity

import com.vibbra.timesheet.domain.authentication.entity.Auth

data class User(
    val id: Long? = null,
    val name: String? = null,
    val auth: Auth? = null,
)