package com.vibbra.timesheet.domain.user.usecase

import com.vibbra.timesheet.domain.user.model.User

interface FindUserUseCase {
    fun findByEmailOrLogin(email: String, login: String): User?
    fun findByEmail(userEmail: String): User?
    fun findById(userId: String): User?
}
