package com.vibbra.timesheet.domain.timerecord.model

import java.time.LocalDateTime

data class TimeRecord(
    var id: Long? = null,
    var startedAt: LocalDateTime?,
    var endedAt: LocalDateTime?
)
