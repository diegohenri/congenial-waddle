package com.vibbra.timesheet.app.user.postgresql.repository

import com.vibbra.timesheet.app.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, String> {
    fun findFirstByEmailOrLogin(email: String, login: String): UserEntity?
    fun findByLogin(username: String): UserEntity?
}
