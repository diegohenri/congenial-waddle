package com.vibbra.timesheet.domain.timerecord.usecase.impl

import com.vibbra.timesheet.domain.exception.BusinessException
import com.vibbra.timesheet.domain.project.dataprovider.FindProjectDataProvider
import com.vibbra.timesheet.domain.timerecord.dataprovider.UpdateTimeRecordDataProvider
import com.vibbra.timesheet.domain.timerecord.model.TimeRecord
import com.vibbra.timesheet.domain.timerecord.usecase.FindTimeRecordUseCase
import com.vibbra.timesheet.domain.timerecord.usecase.UpdateTimeRecordUseCase
import com.vibbra.timesheet.domain.user.model.User
import javax.inject.Named

@Named
class UpdateTimeRecordUseCaseImpl(
    private val findProject: FindProjectDataProvider,
    private val findTimeRecordUseCase: FindTimeRecordUseCase,
    private val updateTimeRecord: UpdateTimeRecordDataProvider
) : UpdateTimeRecordUseCase {

    override fun update(newTimeRecord: TimeRecord, user: User, projectId: Long, timeRecordId: Long): TimeRecord {
        val project = findProject.find(projectId, user.id.orEmpty())
            ?: throw BusinessException("Project not found with id and user informed!")

        val actualTimeRecord = findTimeRecordUseCase.findByIdAndUser(timeRecordId, user.id!!)
            ?: throw BusinessException("Time record not found with id and user informed!")

        if (newTimeRecord.startedAt?.isAfter(newTimeRecord.endedAt) == true) {
            throw BusinessException("The start time must be before the end time!")
        }

        newTimeRecord.apply {
            this.id = actualTimeRecord.id
        }

        return updateTimeRecord.update(newTimeRecord, user, project)
    }
}