package com.vibbra.timesheet.app.timerecord.entity

import com.vibbra.timesheet.app.project.entity.ProjectEntity
import com.vibbra.timesheet.app.user.entity.UserEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "time_record")
data class TimeRecordEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "time_record_sequence")
    @SequenceGenerator(name = "time_record_sequence", allocationSize = 1)
    val id: Long? = null,
    @ManyToOne
    val user: UserEntity? = null,
    @ManyToOne
    val project: ProjectEntity? = null,
    val startedAt: LocalDateTime? = null,
    val endedAt: LocalDateTime? = null
)
