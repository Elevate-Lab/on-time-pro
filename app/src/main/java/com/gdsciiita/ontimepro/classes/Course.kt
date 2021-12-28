package com.gdsciiita.ontimepro.classes

data class Course(
    val alternateLink: String,
    val calendarId: String,
    val courseGroupEmail: String?,
    val courseState: String?,
    val creationTime: String?,
    val descriptionHeading: String?,
    val guardiansEnabled: Boolean?,
    val id: String,
    val name: String,
    var facultyName: String?,
    var classType: String?,
    val ownerId: String?,
    val section: String?,
    val teacherGroupEmail: String?,
    val updateTime: String?
)