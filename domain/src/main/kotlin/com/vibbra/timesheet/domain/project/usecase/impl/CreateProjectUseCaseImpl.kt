package com.vibbra.timesheet.domain.project.usecase.impl

import com.vibbra.timesheet.domain.project.dataprovider.CreateProjectDataProvider
import com.vibbra.timesheet.domain.project.model.Project
import com.vibbra.timesheet.domain.project.usecase.CreateProjectUseCase
import com.vibbra.timesheet.domain.user.dataprovider.FindUserDataProvider
import javax.inject.Named

@Named
class CreateProjectUseCaseImpl(
    private val createProject: CreateProjectDataProvider,
    private val findUser: FindUserDataProvider
) : CreateProjectUseCase {

    override fun create(project: Project, userCodes: List<String>): Project {

        val users = findUser.findAllByIds(userCodes)
        return createProject.create(project, users)
    }
}