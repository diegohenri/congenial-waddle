package com.vibbra.timesheet.domain.user.usecase.impl

import com.vibbra.timesheet.domain.user.dataprovider.FindUserDataProvider
import com.vibbra.timesheet.domain.user.model.User
import com.vibbra.timesheet.domain.user.usecase.FindUserUseCase
import javax.inject.Named

@Named
class FindUserUseCaseImpl(
    private val findUserDataProvider: FindUserDataProvider
) : FindUserUseCase {

    override fun findByEmailOrLogin(email: String, login: String): User? {
        return findUserDataProvider.findByEmailOrLogin(email, login)
    }

    override fun findByEmail(userEmail: String): User? {
        return findUserDataProvider.findByEmail(userEmail)
    }

    override fun findById(userId: String): User? {
        return findUserDataProvider.findById(userId)
    }
}