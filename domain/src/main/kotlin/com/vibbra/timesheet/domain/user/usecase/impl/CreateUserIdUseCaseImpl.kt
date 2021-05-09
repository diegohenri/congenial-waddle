package com.vibbra.timesheet.domain.user.usecase.impl

import com.vibbra.timesheet.domain.user.usecase.CreateUserIdUseCase
import java.util.*
import javax.inject.Named

@Named
class CreateUserIdUseCaseImpl : CreateUserIdUseCase {

    override fun create(): String {
        return UUID.randomUUID().toString()
    }
}