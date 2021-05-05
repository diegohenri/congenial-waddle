package com.vibbra.timesheet.domain.authentication.usecase.impl

import com.vibbra.timesheet.domain.authentication.dataprovider.CipherConfigurationDataProvider
import com.vibbra.timesheet.domain.authentication.usecase.DecryptAuthPasswordUseCase
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.inject.Named

@Named
class DecryptAuthPasswordUseCaseImpl(
    private val cipherConfiguration: CipherConfigurationDataProvider
) : DecryptAuthPasswordUseCase {

    override fun decrypt(password: String): String {
        val config = cipherConfiguration.getCipherConfiguration()
        val cipher = Cipher.getInstance(config.cipherTransformation)
        val secretKey = SecretKeySpec(config.encryptionKey.encodeToByteArray(), config.algorithm)

        cipher.init(Cipher.DECRYPT_MODE, secretKey)

        return cipher.doFinal(password.encodeToByteArray()).decodeToString()
    }
}