package com.vibbra.timesheet.domain.project.usecase

import com.vibbra.timesheet.domain.project.model.Project

interface CreateProjectUseCase {

    fun create(project: Project, userCodes: List<String>): Project
}
