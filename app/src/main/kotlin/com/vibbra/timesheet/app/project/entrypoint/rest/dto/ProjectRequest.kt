package com.vibbra.timesheet.app.project.entrypoint.rest.dto


data class ProjectRequest(
    val title: String,
    val description: String,
    val users: List<String>
)
