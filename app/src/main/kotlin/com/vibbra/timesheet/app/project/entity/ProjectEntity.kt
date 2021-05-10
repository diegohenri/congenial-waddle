package com.vibbra.timesheet.app.project.entity

import com.vibbra.timesheet.app.timerecord.entity.TimeRecordEntity
import com.vibbra.timesheet.app.user.entity.UserEntity
import javax.persistence.*

@Entity
@Table(name = "project")
data class ProjectEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_sequence")
    @SequenceGenerator(name = "project_sequence", allocationSize = 1)
    var id: Long? = null,
    var title: String? = null,
    var description: String? = null,
    @OneToMany
    var timeRecords: List<TimeRecordEntity>? = null,
    @ManyToMany
    @JoinTable(
        name = "projects_users",
        joinColumns = [JoinColumn(name = "project_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    var users: List<UserEntity>? = null
)
