package com.vibbra.timesheet.domain.authentication.usecase.impl

import com.vibbra.timesheet.domain.authentication.dataprovider.CipherConfigurationDataProvider
import com.vibbra.timesheet.domain.authentication.entity.Auth
import com.vibbra.timesheet.domain.authentication.usecase.EncryptAuthPasswordUseCase
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.inject.Named

@Named
class EncryptAuthPasswordUseCaseImpl(
    private val cipherConfiguration: CipherConfigurationDataProvider
) : EncryptAuthPasswordUseCase {

    override fun encrypt(password: String): String {
        val config = cipherConfiguration.getCipherConfiguration()
        val cipher = Cipher.getInstance(config.cipherTransformation)
        val secretKey = SecretKeySpec(config.encryptionKey.encodeToByteArray(), config.algorithm)

        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        return cipher.doFinal(password.encodeToByteArray()).decodeToString()
    }

    override fun encrypt(auth: Auth) {
        auth.apply {
            password = encrypt(this.password)
        }
    }
}