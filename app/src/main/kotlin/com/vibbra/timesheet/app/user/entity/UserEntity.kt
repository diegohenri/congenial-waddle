package com.vibbra.timesheet.app.user.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class UserEntity(

    @Id
    var id: String,
    val name: String,
    val email: String,
    val login: String,
    var password: String,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime
)
