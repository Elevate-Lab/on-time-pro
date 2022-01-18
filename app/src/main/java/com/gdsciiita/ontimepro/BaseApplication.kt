package com.gdsciiita.ontimepro

import android.app.Application
import com.gdsciiita.ontimepro.data.CourseDatabase

class BaseApplication:Application() {

    val database: CourseDatabase by lazy { CourseDatabase.getDatabase(this) }

}