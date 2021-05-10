package com.vibbra.timesheet.domain.timerecord.usecase.impl

import com.vibbra.timesheet.domain.timerecord.dataprovider.FindTimeRecordDataProvider
import com.vibbra.timesheet.domain.timerecord.model.TimeRecord
import com.vibbra.timesheet.domain.timerecord.usecase.FindTimeRecordUseCase
import com.vibbra.timesheet.domain.user.model.User
import javax.inject.Named

@Named
class FindTimeRecordUseCaseImpl(
    private val findTimeRecord: FindTimeRecordDataProvider
) : FindTimeRecordUseCase {

    override fun findAllByProject(projectId: Long, user: User): List<TimeRecord?> {
        return findTimeRecord.findAll(projectId, user)
    }

    override fun findByIdAndUser(timeRecordId: Long, userId: String): TimeRecord? {
        return findTimeRecord.findByIdAndUser(timeRecordId, userId)
    }
}