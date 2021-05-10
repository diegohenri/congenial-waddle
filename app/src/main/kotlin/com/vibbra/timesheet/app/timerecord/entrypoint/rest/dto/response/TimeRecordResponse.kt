package com.vibbra.timesheet.app.timerecord.entrypoint.rest.dto.response

import java.time.LocalDateTime

data class TimeRecordResponse(
    val id: Long? = null,
    val projectId: Long? = null,
    val userId: String? = null,
    val startedAt: LocalDateTime? = null,
    val endedAt: LocalDateTime? = null
)
