package com.vibbra.timesheet.app.security.cipher.configuration

import com.vibbra.timesheet.domain.authentication.dataprovider.CipherConfigurationDataProvider
import com.vibbra.timesheet.domain.authentication.entity.CipherConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "security.cipher")
class CipherConfig : CipherConfigurationDataProvider {

    lateinit var key: String
    lateinit var algorithm: String
    lateinit var operationMode: String
    lateinit var paddingScheme: String

    override fun getCipherConfiguration(): CipherConfiguration {
        return CipherConfiguration(
            encryptionKey = this.key,
            algorithm = this.algorithm,
            operationMode = this.operationMode,
            paddingScheme = this.paddingScheme
        )
    }
}