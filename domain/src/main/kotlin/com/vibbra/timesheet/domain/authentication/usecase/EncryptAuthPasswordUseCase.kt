package com.vibbra.timesheet.domain.authentication.usecase

import com.vibbra.timesheet.domain.authentication.entity.Auth

interface EncryptAuthPasswordUseCase {

    fun encrypt(password: String): String

    fun encrypt(auth: Auth)

}
