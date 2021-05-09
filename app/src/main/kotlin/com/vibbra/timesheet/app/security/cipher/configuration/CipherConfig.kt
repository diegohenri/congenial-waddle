package com.vibbra.timesheet.app.security.cipher.configuration

import com.vibbra.timesheet.domain.authentication.dataprovider.CipherConfigurationDataProvider
import com.vibbra.timesheet.domain.authentication.entity.CipherConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "security.cipher")
class CipherConfig(
    val key: String,
    val algorithm: String,
    val operationMode: String,
    val paddingScheme: String
) : CipherConfigurationDataProvider {

    override fun getCipherConfiguration(): CipherConfiguration {
        return CipherConfiguration(
            encryptionKey = this.key,
            algorithm = this.algorithm,
            operationMode = this.operationMode,
            paddingScheme = this.paddingScheme
        )
    }
}