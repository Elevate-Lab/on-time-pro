package com.gdsciiita.ontimepro.classes

data class Assignment (
    val courseId:String,
    val id:String,
    val creatorUserId:String,
    val alternateLink: String,
    val title:String?,
    var workType:String?,
    var date:String?,
    var state:String?,
    val subject:String?,
    var submissionDay:String?,
    var creationTime:String?,
    var updateTime:String?,
    var submissionTime:String?,
    var maxPoints:Int = 0,
    var pointsReceived:Int=0
)