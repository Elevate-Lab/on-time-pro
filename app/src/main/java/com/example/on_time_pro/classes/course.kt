package com.example.on_time_pro.classes

class course {

    private var courseName: String? = null
    private var facultyName: String? = null
    private var classType: String? = null

    fun getCourseName(): String? {
        return courseName
    }

    fun setCourseName(cName: String) {
        courseName = cName
    }

    fun getFacultyName(): String? {
        return facultyName
    }

    fun setFacultyName(fName: String) {
        facultyName = fName
    }

    fun getClassType(): String? {
        return classType
    }

    fun setclassType(cType: String) {
        classType = cType
    }
}