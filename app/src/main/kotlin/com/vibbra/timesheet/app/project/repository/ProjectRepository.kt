package com.vibbra.timesheet.app.project.repository

import com.vibbra.timesheet.app.project.entity.ProjectEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<ProjectEntity, Long> {

    fun findOneByIdAndUsersId(projectCode: Long, userCode: String): ProjectEntity?
}
