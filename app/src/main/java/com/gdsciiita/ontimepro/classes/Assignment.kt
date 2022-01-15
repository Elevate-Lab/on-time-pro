package com.gdsciiita.ontimepro.classes

data class Assignment (
    val courseId:String?,
    val id:String?,
    val title:String?,
    val description:String?,
    val state:String?,
    val alternateLink: String?,
    val creationTime:String?,
    val updateTime:String?,
    val dueDate:DueDate?,
    val dueTime:DueTime?,
    var maxPoints:Int?,
    var workType:String?,
    val creatorUserId:String?,
    val topicId:String?
)