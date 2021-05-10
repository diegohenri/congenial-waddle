package com.vibbra.timesheet.app.timerecord.dataprovider

import com.vibbra.timesheet.app.timerecord.converter.TimeRecordConverter
import com.vibbra.timesheet.app.timerecord.repository.TimeRecordRepository
import com.vibbra.timesheet.domain.timerecord.dataprovider.FindTimeRecordDataProvider
import com.vibbra.timesheet.domain.timerecord.model.TimeRecord
import com.vibbra.timesheet.domain.user.model.User
import org.springframework.stereotype.Component

@Component
class FindTimeRecordDataProviderImpl(
    private val timeRecordConverter: TimeRecordConverter,
    private val timeRecordRepository: TimeRecordRepository
) : FindTimeRecordDataProvider {

    override fun findAll(projectId: Long, user: User): List<TimeRecord?> {
        return timeRecordRepository
            .findAllByProjectIdAndUserId(projectId, user.id.orEmpty())
            .map { time -> time.takeIf { it != null }?.let { timeRecordConverter.toDomain(it) } }
    }

    override fun findByIdAndUser(timeRecordId: Long, userId: String): TimeRecord? {
        return timeRecordRepository
            .findByIdAndUserId(timeRecordId, userId)
            ?.let { timeRecordConverter.toDomain(it) }
    }
}