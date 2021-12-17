package com.example.on_time_pro.classes

class Assignment {
    private var date:String?=null
    private var status:String?=null
    private var subject:String?=null
    private var submissionDay:String?=null
    private var submissionTime:String?=null
    private var maxPoints:Int = 0
    private var pointsReceived:Int=0

    fun getPointsReceived(): Int {
        return pointsReceived
    }

    fun setPointsReceived(points: Int) {
        pointsReceived = points
    }

    fun getMaxPoints(): Int {
        return maxPoints
    }

    fun setMaxPoints(points: Int) {
        maxPoints = points
    }

    fun getDate(): String? {
        return date
    }

    fun setDate(Date: String) {
        date = Date
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(Status: String) {
        status = Status
    }

    fun getSubject(): String? {
        return subject
    }

    fun setSubject(courseName: String) {
        subject = courseName
    }

    fun getSubmissionDay(): String? {
        return submissionDay
    }

    fun setSubmissionDay(SubmissionDay: String) {
        submissionDay = SubmissionDay
    }

    fun getSubmissionTime(): String? {
        return submissionTime
    }

    fun setSubmissionTime(SubmissionTime: String) {
        submissionTime = SubmissionTime
    }

}