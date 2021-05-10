package com.vibbra.timesheet.domain.timerecord.usecase

import com.vibbra.timesheet.domain.timerecord.model.TimeRecord
import com.vibbra.timesheet.domain.user.model.User

interface TimeRecordUseCase {

    fun register(time: TimeRecord, user: User, projectCode: Long) : TimeRecord
}
