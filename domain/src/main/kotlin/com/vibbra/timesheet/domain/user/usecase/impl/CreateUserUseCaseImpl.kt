package com.vibbra.timesheet.domain.user.usecase.impl

import com.vibbra.timesheet.domain.authentication.usecase.EncryptUserPasswordUseCase
import com.vibbra.timesheet.domain.exception.BusinessException
import com.vibbra.timesheet.domain.user.dataprovider.CreateUserDataProvider
import com.vibbra.timesheet.domain.user.model.User
import com.vibbra.timesheet.domain.user.usecase.CreateUserIdUseCase
import com.vibbra.timesheet.domain.user.usecase.CreateUserUseCase
import com.vibbra.timesheet.domain.user.usecase.FindUserUseCase
import javax.inject.Named

@Named
class CreateUserUseCaseImpl(
    private val encryptAuthPasswordUseCase: EncryptUserPasswordUseCase,
    private val findUserUseCase: FindUserUseCase,
    private val createUserId: CreateUserIdUseCase,
    private val createUser: CreateUserDataProvider
) : CreateUserUseCase {

    override fun create(user: User): User? {
        if (findUserUseCase.find(user) != null) {
            throw BusinessException("User with email and login already exists.")
        }

        user.apply {
            this.id = createUserId.create()
            this.password = encryptAuthPasswordUseCase.encrypt(user.password)
        }

        return createUser.create(user)
    }
}
