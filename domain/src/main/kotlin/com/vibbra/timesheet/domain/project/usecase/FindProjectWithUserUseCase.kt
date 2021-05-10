package com.vibbra.timesheet.domain.project.usecase

import com.vibbra.timesheet.domain.project.model.Project

interface FindProjectWithUserUseCase {

    fun find(projectCode: Long, userCode: String): Project?
}
