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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCourses(courses:List<Course>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(courses: Course)

    @Query("SELECT * from courseList ORDER BY name")
    fun getCourses(): Flow<List<Course>>

}