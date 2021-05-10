package com.vibbra.timesheet.app.timerecord.converter.impl

import com.vibbra.timesheet.app.project.converter.ProjectConverter
import com.vibbra.timesheet.app.timerecord.converter.TimeRecordConverter
import com.vibbra.timesheet.app.timerecord.entity.TimeRecordEntity
import com.vibbra.timesheet.app.timerecord.entrypoint.rest.dto.request.TimeRecordRequest
import com.vibbra.timesheet.app.timerecord.entrypoint.rest.dto.response.TimeRecordResponse
import com.vibbra.timesheet.app.user.converter.UserConverter
import com.vibbra.timesheet.domain.project.model.Project
import com.vibbra.timesheet.domain.timerecord.model.TimeRecord
import com.vibbra.timesheet.domain.user.model.User
import org.springframework.stereotype.Component

@Component
class TimeRecordConverterImpl(
    private val userConverter: UserConverter,
    private val projectConverter: ProjectConverter
) : TimeRecordConverter {

    override fun toEntity(timeRecord: TimeRecord, user: User, project: Project): TimeRecordEntity {

        return TimeRecordEntity(
            id = timeRecord.id,
            user = userConverter.toEntity(user),
            project = projectConverter.toEntity(project),
            startedAt = timeRecord.startedAt,
            endedAt = timeRecord.endedAt
        )
    }

    override fun toDomain(timeEntity: TimeRecordEntity): TimeRecord {
        return timeEntity.let {
            TimeRecord(
                id = it.id,
                startedAt = it.startedAt,
                endedAt = it.endedAt
            )
        }
    }

    override fun toDomain(timeRequest: TimeRecordRequest): TimeRecord {
        return TimeRecord(
            startedAt = timeRequest.startedAt,
            endedAt = timeRequest.endedAt
        )
    }

    override fun toResponse(timeDomain: TimeRecord, user: User, projectCode: Long): TimeRecordResponse {
        return TimeRecordResponse(
            id = timeDomain.id,
            projectId = projectCode,
            userId = user.id,
            startedAt = timeDomain.startedAt,
            endedAt = timeDomain.endedAt
        )
    }
}