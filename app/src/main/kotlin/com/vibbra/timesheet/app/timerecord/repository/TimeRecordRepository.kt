package com.vibbra.timesheet.app.timerecord.repository

import com.vibbra.timesheet.app.timerecord.entity.TimeRecordEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TimeRecordRepository : JpaRepository<TimeRecordEntity, Long>