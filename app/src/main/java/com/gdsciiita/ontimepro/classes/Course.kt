package com.gdsciiita.ontimepro.classes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courseList")
data class Course(
    @PrimaryKey
    val id: String,
    val alternateLink: String,
    val calendarId: String,
    val courseGroupEmail: String?,
    val courseState: String?,
    val creationTime: String?,
    val descriptionHeading: String?,
    val guardiansEnabled: Boolean?,
    val name: String,
    var facultyName: String?,
    var classType: String?,
    val ownerId: String?,
    val section: String?,
    val teacherGroupEmail: String?,
    val updateTime: String?
)