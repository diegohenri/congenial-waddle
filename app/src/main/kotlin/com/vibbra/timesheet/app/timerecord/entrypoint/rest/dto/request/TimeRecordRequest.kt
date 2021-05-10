package com.vibbra.timesheet.app.timerecord.entrypoint.rest.dto.request

import java.time.LocalDateTime

data class TimeRecordRequest(
    val startedAt: LocalDateTime? = null,

    val endedAt: LocalDateTime? = null
)
