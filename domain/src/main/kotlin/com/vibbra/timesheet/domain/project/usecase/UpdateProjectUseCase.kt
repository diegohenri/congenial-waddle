package com.vibbra.timesheet.domain.project.usecase

import com.vibbra.timesheet.domain.project.model.Project
import com.vibbra.timesheet.domain.user.model.User

interface UpdateProjectUseCase {

    fun update(projectId: Long, newProject: Project, user: User, userCodes: List<String>): Project
}
