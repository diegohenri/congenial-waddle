package com.vibbra.timesheet.domain.timerecord.usecase

import com.vibbra.timesheet.domain.timerecord.model.TimeRecord
import com.vibbra.timesheet.domain.user.model.User

interface FindTimeRecordUseCase {

    fun findAllByProject(projectId: Long, user: User): List<TimeRecord?>
    fun findByIdAndUser(timeRecordId: Long, userId: String): TimeRecord?
}
