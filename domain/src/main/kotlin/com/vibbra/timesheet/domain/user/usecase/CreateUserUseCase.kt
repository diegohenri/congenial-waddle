package com.vibbra.timesheet.domain.user.usecase

import com.vibbra.timesheet.domain.user.model.User

interface CreateUserUseCase {

    fun create(user: User): User?
}
