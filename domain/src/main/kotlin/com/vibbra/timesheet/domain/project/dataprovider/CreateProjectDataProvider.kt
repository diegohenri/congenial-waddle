package com.vibbra.timesheet.domain.project.dataprovider

import com.vibbra.timesheet.domain.project.model.Project
import com.vibbra.timesheet.domain.user.model.User

interface CreateProjectDataProvider {

    fun create(project: Project, users: List<User>): Project
}
