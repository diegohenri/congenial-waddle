package com.vibbra.timesheet.domain.user.usecase

import com.vibbra.timesheet.domain.user.model.User

interface FindUserUseCase {
    fun find(user: User): User?
}
