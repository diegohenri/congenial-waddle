package com.vibbra.timesheet.domain.appointment.model

import java.time.LocalDateTime

data class AppointmentTime(
    var startTime: LocalDateTime? = null,
    var endTime: LocalDateTime? = null
)