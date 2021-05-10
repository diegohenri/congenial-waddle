package com.vibbra.timesheet.app.project.converter.impl

import com.vibbra.timesheet.app.project.converter.ProjectConverter
import com.vibbra.timesheet.app.project.entity.ProjectEntity
import com.vibbra.timesheet.app.project.entrypoint.rest.dto.ProjectRequest
import com.vibbra.timesheet.app.project.entrypoint.rest.dto.ProjectResponse
import com.vibbra.timesheet.domain.project.model.Project
import org.springframework.stereotype.Component

@Component
class ProjectConverterImpl : ProjectConverter {

    override fun toEntity(projectDomain: Project): ProjectEntity {

        return projectDomain.let {
            ProjectEntity(
                id = it.id,
                title = it.title,
                description = it.description
            )
        }
    }

    override fun toDomain(projectEntity: ProjectEntity): Project {
        return Project(
            id = projectEntity.id,
            title = projectEntity.title,
            description = projectEntity.description
        )
    }

    override fun toDomain(projectRequest: ProjectRequest): Project {
        return Project(
            title = projectRequest.title,
            description = projectRequest.description
        )
    }

    override fun toResponse(projectDomain: Project): ProjectResponse {
        return ProjectResponse(
            id = projectDomain.id,
            title = projectDomain.title,
            description = projectDomain.description
        )
    }
}