package com.vibbra.timesheet.app.project.entrypoint.rest.controller

import com.vibbra.timesheet.app.project.converter.ProjectConverter
import com.vibbra.timesheet.app.project.entrypoint.rest.dto.ProjectRequest
import com.vibbra.timesheet.app.project.entrypoint.rest.dto.ProjectResponse
import com.vibbra.timesheet.domain.project.usecase.CreateProjectUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/v1/projects")
@RestController
class ProjectController(
    private val projectConverter: ProjectConverter,
    private val createProject: CreateProjectUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProject(@RequestBody projectRequest: ProjectRequest): ProjectResponse? {

        val project = projectConverter.toDomain(projectRequest)
        return createProject
            .create(project = project, userCodes = projectRequest.users)
            .let {
                projectConverter.toResponse(it)
            }
    }
}