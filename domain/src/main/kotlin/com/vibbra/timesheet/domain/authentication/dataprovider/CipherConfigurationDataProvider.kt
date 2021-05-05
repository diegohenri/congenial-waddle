package com.vibbra.timesheet.domain.authentication.dataprovider

import com.vibbra.timesheet.domain.authentication.entity.CipherConfiguration

interface CipherConfigurationDataProvider {

    fun getCipherConfiguration(): CipherConfiguration
}