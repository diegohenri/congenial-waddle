package com.vibbra.timesheet.app.timerecord.dataprovider

import com.vibbra.timesheet.app.timerecord.converter.TimeRecordConverter
import com.vibbra.timesheet.app.timerecord.repository.TimeRecordRepository
import com.vibbra.timesheet.domain.project.model.Project
import com.vibbra.timesheet.domain.timerecord.dataprovider.UpdateTimeRecordDataProvider
import com.vibbra.timesheet.domain.timerecord.model.TimeRecord
import com.vibbra.timesheet.domain.user.model.User
import org.springframework.stereotype.Component

@Component
class UpdateTimeRecordDataProviderImpl(
    private val timeRecordConverter: TimeRecordConverter,
    private val timeRecordRepository: TimeRecordRepository
) : UpdateTimeRecordDataProvider {

    override fun update(newTimeRecord: TimeRecord, user: User, project: Project): TimeRecord {
        val entity = timeRecordConverter.toEntity(timeRecord = newTimeRecord, user = user, project = project)
        return timeRecordRepository
            .save(entity)
            .let {
                timeRecordConverter.toDomain(it)
            }
    }
}