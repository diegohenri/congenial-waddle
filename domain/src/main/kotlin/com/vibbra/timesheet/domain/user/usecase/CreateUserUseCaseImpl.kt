package com.vibbra.timesheet.domain.user.usecase

import com.vibbra.timesheet.domain.authentication.usecase.EncryptAuthPasswordUseCase
import com.vibbra.timesheet.domain.user.entity.User
import javax.inject.Named

@Named
class CreateUserUseCaseImpl(
    private val encryptAuthPasswordUseCase: EncryptAuthPasswordUseCase,
    private val findUserUseCase : FindUserUseCase
) : CreateUserUseCase {

    override fun create(user: User): User? {
        encryptAuthPasswordUseCase.encrypt(user.auth)
        findUserUseCase.find(user.auth)
        return null
    }
}
