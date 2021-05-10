package com.vibbra.timesheet.app.security.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "security.jwt")
data class SecurityConfigurationProperties(
    var secret: String,
    val expirationTime: Long
)
