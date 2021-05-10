package com.vibbra.timesheet.app.project.converter

import com.vibbra.timesheet.app.project.entity.ProjectEntity
import com.vibbra.timesheet.app.project.entrypoint.rest.dto.ProjectRequest
import com.vibbra.timesheet.app.project.entrypoint.rest.dto.ProjectResponse
import com.vibbra.timesheet.domain.project.model.Project

interface ProjectConverter {

    fun toEntity(projectDomain: Project): ProjectEntity
    fun toDomain(projectRequest: ProjectRequest): Project
    fun toResponse(projectDomain: Project): ProjectResponse
    fun toDomain(projectEntity: ProjectEntity): Project
}
