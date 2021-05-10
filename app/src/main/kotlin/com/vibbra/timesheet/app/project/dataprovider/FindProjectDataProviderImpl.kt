package com.vibbra.timesheet.app.project.dataprovider

import com.vibbra.timesheet.app.project.converter.ProjectConverter
import com.vibbra.timesheet.app.project.repository.ProjectRepository
import com.vibbra.timesheet.domain.project.dataprovider.FindProjectDataProvider
import com.vibbra.timesheet.domain.project.model.Project
import org.springframework.stereotype.Component

@Component
class FindProjectDataProviderImpl(
    private val projectConverter: ProjectConverter,
    private val projectRepository: ProjectRepository
) : FindProjectDataProvider {

    override fun find(projectCode: Long, userCode: String): Project? {
        return projectRepository
            .findOneByIdAndUsersId(projectCode, userCode)
            ?.let { projectConverter.toDomain(it) }
    }

    override fun findAll(): List<Project?> {
        return projectRepository
            .findAll()
            .map { projectConverter.toDomain(it) }
    }

    override fun findById(projectId: Long): Project? {
        return projectRepository
            .findById(projectId)
            .takeIf { it.isPresent }
            ?.get()
            ?.let {
                projectConverter.toDomain(it)
            }
    }
}