package com.vibbra.timesheet.app.security.cipher.encryption

import com.vibbra.timesheet.domain.authentication.usecase.EncryptUserPasswordUseCase
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncryptorImpl(
    private val encryption: EncryptUserPasswordUseCase,
) : PasswordEncoder {

    override fun encode(rawPassword: CharSequence): String? {
        return encryption.encrypt(rawPassword.toString())
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        return encryption.encrypt(rawPassword.toString()) == encodedPassword
    }
}