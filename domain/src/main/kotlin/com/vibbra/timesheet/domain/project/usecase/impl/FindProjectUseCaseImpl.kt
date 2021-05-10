package com.vibbra.timesheet.domain.project.usecase.impl

import com.vibbra.timesheet.domain.project.dataprovider.FindProjectDataProvider
import com.vibbra.timesheet.domain.project.model.Project
import com.vibbra.timesheet.domain.project.usecase.FindProjectUseCase
import javax.inject.Named

@Named
class FindProjectUseCaseImpl(
    private val findProject : FindProjectDataProvider
) : FindProjectUseCase {
    override fun getAll(): List<Project?> {
        return findProject.findAll()
    }

    override fun getOne(projectId: Long): Project? {
        return findProject.findById(projectId)
    }

    override fun find(projectCode: Long, userCode: String): Project? {
        return findProject.find(projectCode, userCode)
    }
}