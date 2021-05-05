package com.vibbra.timesheet.app.security.configuration

import com.vibbra.timesheet.domain.authentication.dataprovider.CipherConfigurationDataProvider
import com.vibbra.timesheet.domain.authentication.entity.CipherConfiguration

class CipherConfig : CipherConfigurationDataProvider {
    override fun getCipherConfiguration(): CipherConfiguration {
        return CipherConfiguration(
            encryptionKey = "thisisa128bitkey",
            algorithm = "AES",
            operationMode = "ECB",
            paddingScheme = "PKCS5Padding"
        )
    }
}