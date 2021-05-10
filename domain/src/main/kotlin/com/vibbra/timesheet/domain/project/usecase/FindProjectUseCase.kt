package com.vibbra.timesheet.domain.project.usecase

import com.vibbra.timesheet.domain.project.model.Project

interface FindProjectUseCase {

    fun getAll(): List<Project?>
    fun getOne(projectId: Long): Project?
    fun find(projectCode: Long, userCode: String): Project?
}
