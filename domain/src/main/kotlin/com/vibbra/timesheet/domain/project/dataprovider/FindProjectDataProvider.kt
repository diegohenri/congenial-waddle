package com.vibbra.timesheet.domain.project.dataprovider

import com.vibbra.timesheet.domain.project.model.Project

interface FindProjectDataProvider {

    fun find(projectCode: Long, userCode: String): Project?
}
