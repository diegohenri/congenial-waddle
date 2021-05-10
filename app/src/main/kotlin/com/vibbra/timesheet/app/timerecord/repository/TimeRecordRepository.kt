package com.vibbra.timesheet.app.timerecord.repository

import com.vibbra.timesheet.app.timerecord.entity.TimeRecordEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TimeRecordRepository : JpaRepository<TimeRecordEntity, Long> {
    fun findAllByProjectIdAndUserId(projectId: Long, userId: String): List<TimeRecordEntity?>
    fun findByIdAndUserId(timeRecordId: Long, userId: String): TimeRecordEntity?
}