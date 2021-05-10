package com.vibbra.timesheet.domain.project.usecase

import com.vibbra.timesheet.domain.project.dataprovider.FindProjectDataProvider
import com.vibbra.timesheet.domain.project.model.Project
import javax.inject.Named

@Named
class FindProjectWithUserUseCaseImpl(
    private val findProject : FindProjectDataProvider
) : FindProjectWithUserUseCase {

    override fun find(projectCode: Long, userCode: String): Project? {
        return findProject.find(projectCode, userCode)
    }
}