package com.vibbra.timesheet.domain.project.dataprovider

import com.vibbra.timesheet.domain.project.model.Project
import com.vibbra.timesheet.domain.user.model.User

interface SaveProjectDataProvider {

    fun save(project: Project, users: List<User>): Project
}
