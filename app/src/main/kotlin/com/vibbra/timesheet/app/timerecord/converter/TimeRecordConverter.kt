package com.vibbra.timesheet.app.timerecord.converter

import com.vibbra.timesheet.app.timerecord.entity.TimeRecordEntity
import com.vibbra.timesheet.app.timerecord.entrypoint.rest.dto.request.TimeRecordRequest
import com.vibbra.timesheet.app.timerecord.entrypoint.rest.dto.response.TimeRecordResponse
import com.vibbra.timesheet.domain.project.model.Project
import com.vibbra.timesheet.domain.timerecord.model.TimeRecord
import com.vibbra.timesheet.domain.user.model.User

interface TimeRecordConverter {

    fun toEntity(timeRecord: TimeRecord, user: User, project: Project): TimeRecordEntity
    fun toDomain(timeEntity: TimeRecordEntity): TimeRecord
    fun toDomain(timeRequest: TimeRecordRequest): TimeRecord
    fun toResponse(timeDomain: TimeRecord, user: User, projectCode: Long): TimeRecordResponse
}
