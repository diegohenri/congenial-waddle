package com.vibbra.timesheet.domain.user.usecase.impl

import com.vibbra.timesheet.domain.user.dataprovider.FindUserDataProvider
import com.vibbra.timesheet.domain.user.model.User
import com.vibbra.timesheet.domain.user.usecase.FindUserUseCase
import javax.inject.Named

@Named
class FindUserUseCaseImpl(
    private val findUserDataProvider : FindUserDataProvider
) : FindUserUseCase {

    override fun find(user: User): User? {
        return findUserDataProvider.findByEmailOrLogin(user.email, user.login)
    }
}