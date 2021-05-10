package com.vibbra.timesheet.domain.user.usecase

import com.vibbra.timesheet.domain.user.model.User

interface UpdateUserUseCase {

    fun update(actualUser: User, newUserInformation: User): User?
}