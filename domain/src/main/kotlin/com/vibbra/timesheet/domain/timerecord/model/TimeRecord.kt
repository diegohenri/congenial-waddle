package com.vibbra.timesheet.domain.timerecord.model

import java.time.LocalDateTime

data class TimeRecord(
    val id: Long? = null,
    val startedAt: LocalDateTime?,
    val endedAt: LocalDateTime?
)
