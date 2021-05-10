package com.vibbra.timesheet.domain.user.dataprovider

import com.vibbra.timesheet.domain.user.model.User

interface UpdateUserDataProvider {

    fun update(user: User): User
}
