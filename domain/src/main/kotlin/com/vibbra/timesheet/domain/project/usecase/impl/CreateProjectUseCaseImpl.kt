package com.vibbra.timesheet.domain.project.usecase.impl

import com.vibbra.timesheet.domain.project.dataprovider.SaveProjectDataProvider
import com.vibbra.timesheet.domain.project.model.Project
import com.vibbra.timesheet.domain.project.usecase.CreateProjectUseCase
import com.vibbra.timesheet.domain.user.dataprovider.FindUserDataProvider
import com.vibbra.timesheet.domain.user.model.User
import javax.inject.Named

@Named
class CreateProjectUseCaseImpl(
    private val saveProject: SaveProjectDataProvider,
    private val findUser: FindUserDataProvider
) : CreateProjectUseCase {

    override fun create(project: Project, user: User, userCodes: List<String>): Project {

        val users = findUser
            .findAllByIds(userCodes)
            .toMutableList()
        users.add(user)

        return saveProject.save(project, users.toList())
    }
}