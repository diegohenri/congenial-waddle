package com.vibbra.timesheet.app.timerecord.entrypoint.rest.controller

import com.vibbra.timesheet.app.timerecord.converter.TimeRecordConverter
import com.vibbra.timesheet.app.timerecord.entrypoint.rest.dto.request.TimeRecordRequest
import com.vibbra.timesheet.app.timerecord.entrypoint.rest.dto.response.TimeRecordResponse
import com.vibbra.timesheet.app.user.converter.UserConverter
import com.vibbra.timesheet.app.user.entity.UserAuthenticationWrapper
import com.vibbra.timesheet.domain.timerecord.usecase.TimeRecordUseCase
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RequestMapping("/v1")
@RestController
class TimeRecordController(
    val timeRecordConverter: TimeRecordConverter,
    val userConverter: UserConverter,
    val timeRecord: TimeRecordUseCase
) {

    @PostMapping("/projects/{projectId}/times")
    @ResponseStatus(HttpStatus.CREATED)
    fun recordTime(
        @RequestBody timeRecordRequest: TimeRecordRequest,
        @PathVariable("projectId") projectId: Long,
        auth: Authentication
    ): TimeRecordResponse {
        val time = timeRecordRequest.let { timeRecordConverter.toDomain(it) }
        val user = (auth.principal as UserAuthenticationWrapper).user.let { userConverter.toDomain(it) }

        return timeRecord
            .register(time = time, user = user, projectCode = projectId)
            .let {
                timeRecordConverter.toResponse(it, user = user, projectCode = projectId)
            }
    }
}