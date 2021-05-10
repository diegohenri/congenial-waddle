package com.vibbra.timesheet.domain.timerecord.usecase

import com.vibbra.timesheet.domain.timerecord.model.TimeRecord
import com.vibbra.timesheet.domain.user.model.User

interface UpdateTimeRecordUseCase {

    fun update(newTimeRecord: TimeRecord, user: User, projectId: Long, timeRecordId: Long): TimeRecord
}
