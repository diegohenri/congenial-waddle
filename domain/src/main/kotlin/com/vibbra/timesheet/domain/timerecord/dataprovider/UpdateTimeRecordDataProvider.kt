package com.vibbra.timesheet.domain.timerecord.dataprovider

import com.vibbra.timesheet.domain.project.model.Project
import com.vibbra.timesheet.domain.timerecord.model.TimeRecord
import com.vibbra.timesheet.domain.user.model.User

interface UpdateTimeRecordDataProvider {

    fun update(newTimeRecord: TimeRecord, user: User, project: Project): TimeRecord
}
