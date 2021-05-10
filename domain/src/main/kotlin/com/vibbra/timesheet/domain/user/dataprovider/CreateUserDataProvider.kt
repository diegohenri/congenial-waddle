package com.vibbra.timesheet.domain.user.dataprovider

import com.vibbra.timesheet.domain.user.model.User

interface CreateUserDataProvider {

    fun create(user: User): User?
}
