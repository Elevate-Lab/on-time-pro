package com.gdsciiita.ontimepro.classes

import com.squareup.moshi.Json

class AssignmentResponse (@Json(name="courseWork") val assignmentList: List<Assignment>?)