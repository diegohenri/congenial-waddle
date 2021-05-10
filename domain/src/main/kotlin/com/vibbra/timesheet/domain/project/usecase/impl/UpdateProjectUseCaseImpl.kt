package com.vibbra.timesheet.domain.project.usecase.impl

import com.vibbra.timesheet.domain.exception.BusinessException
import com.vibbra.timesheet.domain.project.dataprovider.SaveProjectDataProvider
import com.vibbra.timesheet.domain.project.model.Project
import com.vibbra.timesheet.domain.project.usecase.FindProjectUseCase
import com.vibbra.timesheet.domain.project.usecase.UpdateProjectUseCase
import com.vibbra.timesheet.domain.user.dataprovider.FindUserDataProvider
import com.vibbra.timesheet.domain.user.model.User
import javax.inject.Named

@Named
class UpdateProjectUseCaseImpl(
    private val findProject: FindProjectUseCase,
    private val findUser: FindUserDataProvider,
    private val saveProject: SaveProjectDataProvider

) : UpdateProjectUseCase {
    override fun update(projectId: Long, newProject: Project, user: User, userCodes: List<String>): Project {
        val actualProject = findProject.find(projectId, user.id.orEmpty())
            ?: throw BusinessException("Project not found")
        val users = findUser.findAllByIds(userCodes)

        newProject.apply {
            this.id = actualProject.id
        }

        return saveProject.save(newProject, users)
    }
}