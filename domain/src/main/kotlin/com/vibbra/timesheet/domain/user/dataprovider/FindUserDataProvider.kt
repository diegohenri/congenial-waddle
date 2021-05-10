package com.vibbra.timesheet.domain.user.dataprovider

import com.vibbra.timesheet.domain.user.model.User

interface FindUserDataProvider {

    fun findByEmailOrLogin(email: String, login: String): User?
    fun findAllByIds(userCodes: List<String>) : List<User>
}
