package com.vibbra.timesheet.app.project.entrypoint.rest.controller

import com.vibbra.timesheet.app.project.converter.ProjectConverter
import com.vibbra.timesheet.app.project.entrypoint.rest.dto.ProjectRequest
import com.vibbra.timesheet.app.project.entrypoint.rest.dto.ProjectResponse
import com.vibbra.timesheet.app.user.converter.UserConverter
import com.vibbra.timesheet.app.user.entity.UserAuthenticationWrapper
import com.vibbra.timesheet.domain.project.usecase.CreateProjectUseCase
import com.vibbra.timesheet.domain.project.usecase.FindProjectUseCase
import com.vibbra.timesheet.domain.project.usecase.UpdateProjectUseCase
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@Api("Project Information")
@RequestMapping("/v1/projects")
@RestController
class ProjectController(
    private val userConverter: UserConverter,
    private val projectConverter: ProjectConverter,
    private val createProject: CreateProjectUseCase,
    private val updateProject: UpdateProjectUseCase,
    private val findProject: FindProjectUseCase,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProject(@RequestBody projectRequest: ProjectRequest, @AuthenticationPrincipal auth: UserAuthenticationWrapper): ProjectResponse? {
        val user = userConverter.toDomain(auth.user)
        val project = projectConverter.toDomain(projectRequest)
        return createProject
            .create(project = project, userCodes = projectRequest.users, user = user)
            .let {
                projectConverter.toResponse(it)
            }
    }

    @PutMapping("/{projectId}")
    fun updateProject(@RequestBody projectRequest: ProjectRequest, @PathVariable projectId: Long, @AuthenticationPrincipal auth: UserAuthenticationWrapper): ProjectResponse? {
        val user = userConverter.toDomain(auth.user)

        val project = projectConverter.toDomain(projectRequest)
        return updateProject
            .update(projectId = projectId, newProject = project, userCodes = projectRequest.users, user = user)
            .let {
                projectConverter.toResponse(it)
            }
    }


    @GetMapping("/{projectId}")
    fun getProject(@PathVariable projectId: Long): ProjectResponse? {
        return findProject
            .getOne(projectId)
            .takeIf { it != null }
            ?.let { projectConverter.toResponse(it) }
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @GetMapping
    fun listProjects(): List<ProjectResponse?> {
        return findProject
            .getAll()
            .map { project ->
                project.takeIf { it != null }?.let { projectConverter.toResponse(it) }
            }
    }
}