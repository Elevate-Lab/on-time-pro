package com.gdsciiita.ontimepro.classes

import com.squareup.moshi.Json

class CourseResponse (@Json(name="courses") val courseList: List<Course>)