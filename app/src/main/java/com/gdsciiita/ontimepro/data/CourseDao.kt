package com.gdsciiita.ontimepro.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gdsciiita.ontimepro.classes.Course
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for database interaction.
 */

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCourses(courses:List<Course>)

    @Query("SELECT * from courseList")
    fun getCourses(): Flow<List<Course>>

}