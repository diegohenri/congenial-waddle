package com.vibbra.timesheet.app.timerecord.dataprovider

import com.vibbra.timesheet.app.timerecord.converter.TimeRecordConverter
import com.vibbra.timesheet.app.timerecord.repository.TimeRecordRepository
import com.vibbra.timesheet.domain.project.model.Project
import com.vibbra.timesheet.domain.timerecord.dataprovider.CreateTimeRecordDataProvider
import com.vibbra.timesheet.domain.timerecord.model.TimeRecord
import com.vibbra.timesheet.domain.user.model.User
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class CreateTimeRecordDataProviderImpl(
    private val timeRecordConverter: TimeRecordConverter,
    private val repository: TimeRecordRepository
) : CreateTimeRecordDataProvider {


    override fun create(time: TimeRecord, user: User, project: Project): TimeRecord {
        val timeRecordEntity = timeRecordConverter.toEntity(time, user, project)
        return repository.save(timeRecordEntity).let { timeRecordConverter.toDomain(it) }
    }
}