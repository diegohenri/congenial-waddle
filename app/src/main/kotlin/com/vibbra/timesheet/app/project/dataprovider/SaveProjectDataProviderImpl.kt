package com.vibbra.timesheet.app.project.dataprovider

import com.vibbra.timesheet.app.project.converter.ProjectConverter
import com.vibbra.timesheet.app.project.repository.ProjectRepository
import com.vibbra.timesheet.app.user.converter.UserConverter
import com.vibbra.timesheet.domain.project.dataprovider.SaveProjectDataProvider
import com.vibbra.timesheet.domain.project.model.Project
import com.vibbra.timesheet.domain.user.model.User
import org.springframework.stereotype.Component

@Component
class SaveProjectDataProviderImpl(
    private val userConverter: UserConverter,
    private val projectConverter: ProjectConverter,
    private val projectRepository: ProjectRepository
) : SaveProjectDataProvider {

    override fun save(project: Project, users: List<User>): Project {

        val projectEntity = projectConverter
            .toEntity(project)
            .apply {
                this.users = users.map { user -> user.let { userConverter.toEntity(it) } }
            }

        return projectRepository.save(projectEntity).let { projectConverter.toDomain(it) }
    }
}