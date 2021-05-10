package com.vibbra.timesheet.app.timerecord.entrypoint.rest.controller

import com.vibbra.timesheet.app.timerecord.converter.TimeRecordConverter
import com.vibbra.timesheet.app.timerecord.entrypoint.rest.dto.request.TimeRecordRequest
import com.vibbra.timesheet.app.timerecord.entrypoint.rest.dto.response.TimeRecordResponse
import com.vibbra.timesheet.app.user.converter.UserConverter
import com.vibbra.timesheet.app.user.entity.UserAuthenticationWrapper
import com.vibbra.timesheet.domain.timerecord.usecase.FindTimeRecordUseCase
import com.vibbra.timesheet.domain.timerecord.usecase.RegisterTimeRecordUseCase
import com.vibbra.timesheet.domain.timerecord.usecase.UpdateTimeRecordUseCase
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/v1")
@RestController
class TimeRecordController(
    val timeRecordConverter: TimeRecordConverter,
    val userConverter: UserConverter,
    val registerTimeRecord: RegisterTimeRecordUseCase,
    val updateTimeRecord: UpdateTimeRecordUseCase,
    val findTimeRecord: FindTimeRecordUseCase
) {

    @PostMapping("/projects/{projectId}/times")
    @ResponseStatus(HttpStatus.CREATED)
    fun recordTime(
        @RequestBody timeRecordRequest: TimeRecordRequest,
        @PathVariable("projectId") projectId: Long,
        @AuthenticationPrincipal auth: UserAuthenticationWrapper
    ): TimeRecordResponse {
        val time = timeRecordRequest.let { timeRecordConverter.toDomain(it) }
        val user = auth.user.let { userConverter.toDomain(it) }

        return registerTimeRecord
            .register(time = time, user = user, projectCode = projectId)
            .let {
                timeRecordConverter.toResponse(it, user = user, projectCode = projectId)
            }
    }

    @GetMapping("/projects/{projectId}/times")
    fun getTimeRecords(
        @PathVariable("projectId") projectId: Long,
        @AuthenticationPrincipal auth: UserAuthenticationWrapper
    ): List<TimeRecordResponse?> {
        val user = auth.user.let { userConverter.toDomain(it) }
        return findTimeRecord
            .findAllByProject(projectId, user)
            .map { time -> time.takeIf { it != null }?.let { timeRecordConverter.toResponse(timeDomain = it, user = user, projectCode = projectId) } }
    }

    @PutMapping("/projects/{projectId}/times/{timeRecordId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun recordTime(
        @RequestBody timeRecordRequest: TimeRecordRequest,
        @PathVariable("projectId") projectId: Long,
        @PathVariable("timeRecordId") timeRecordId: Long,
        @AuthenticationPrincipal auth: UserAuthenticationWrapper
    ): TimeRecordResponse {
        val time = timeRecordRequest.let { timeRecordConverter.toDomain(it) }
        val user = auth.user.let { userConverter.toDomain(it) }

        return updateTimeRecord
            .update(newTimeRecord = time, user = user, projectId = projectId, timeRecordId = timeRecordId)
            .let {
                timeRecordConverter.toResponse(it, user = user, projectCode = projectId)
            }
    }
}