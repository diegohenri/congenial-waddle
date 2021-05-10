package com.vibbra.timesheet.domain.user.usecase.impl

import com.vibbra.timesheet.domain.exception.BusinessException
import com.vibbra.timesheet.domain.user.dataprovider.UpdateUserDataProvider
import com.vibbra.timesheet.domain.user.model.User
import com.vibbra.timesheet.domain.user.usecase.FindUserUseCase
import com.vibbra.timesheet.domain.user.usecase.UpdateUserUseCase
import java.time.LocalDateTime
import javax.inject.Named

@Named
class UpdateUserUseCaseImpl(
    private val findUserUseCase: FindUserUseCase,
    private val updateUserDataProvider : UpdateUserDataProvider
) : UpdateUserUseCase {

    override fun update(actualUser: User, newUserInformation: User): User {
        val newUserEmail = newUserInformation.email
        if (newUserEmail != actualUser.email && findUserUseCase.findByEmail(newUserEmail) != null) {
            throw BusinessException("Email is already in use.")
        }

        val user = actualUser.id?.let { findUserUseCase.findById(actualUser.id!!) }
            ?: throw BusinessException("User not found.")

        user.apply {
            this.name = newUserInformation.name
            this.email = newUserEmail
            this.updatedAt = LocalDateTime.now()
        }

        return updateUserDataProvider.update(user)
    }
}