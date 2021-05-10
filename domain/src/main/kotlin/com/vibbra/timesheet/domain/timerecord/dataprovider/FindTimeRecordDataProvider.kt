package com.vibbra.timesheet.domain.timerecord.dataprovider

import com.vibbra.timesheet.domain.timerecord.model.TimeRecord
import com.vibbra.timesheet.domain.user.model.User

interface FindTimeRecordDataProvider {

    fun findAll(projectId: Long, user: User): List<TimeRecord?>
    fun findByIdAndUser(timeRecordId: Long, userId: String): TimeRecord?
}
