package com.vibbra.timesheet.domain.timerecord.usecase.impl

import com.vibbra.timesheet.domain.exception.BusinessException
import com.vibbra.timesheet.domain.project.usecase.FindProjectWithUserUseCase
import com.vibbra.timesheet.domain.timerecord.dataprovider.CreateTimeRecordDataProvider
import com.vibbra.timesheet.domain.timerecord.model.TimeRecord
import com.vibbra.timesheet.domain.timerecord.usecase.TimeRecordUseCase
import com.vibbra.timesheet.domain.user.model.User
import javax.inject.Named

@Named
class TimeRecordUseCaseImpl(
    private val findProjectWithUser: FindProjectWithUserUseCase,
    private val createTimeRecord: CreateTimeRecordDataProvider
) : TimeRecordUseCase {

    override fun register(time: TimeRecord, user: User, projectCode: Long): TimeRecord {

        val project = findProjectWithUser.find(projectCode, user.id!!)
            ?: throw BusinessException("Project not found with id and user informed!")

        if (time.startedAt?.isAfter(time.endedAt) == true) {
            throw BusinessException("The start time must be before the end time!")
        }

        return createTimeRecord.create(time, user, project)
    }
}