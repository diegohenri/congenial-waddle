package com.vibbra.timesheet.domain.project.usecase

import com.vibbra.timesheet.domain.project.model.Project

interface UpdateProjectUseCase {

    fun update(projectId: Long, newProject: Project, userCodes: List<String>): Project
}
