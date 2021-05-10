package com.vibbra.timesheet.domain.authentication.usecase

interface EncryptUserPasswordUseCase {

    fun encrypt(password: String): String

}
