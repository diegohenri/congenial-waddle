package com.vibbra.timesheet.domain.authentication.usecase

interface DecryptAuthPasswordUseCase {

    fun decrypt(password: String): String
}
