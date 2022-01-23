package com.gdsciiita.ontimepro.viewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.gdsciiita.ontimepro.classes.Assignment
import com.gdsciiita.ontimepro.classes.Course
import com.gdsciiita.ontimepro.classes.User
import com.gdsciiita.ontimepro.data.CourseDao
import com.gdsciiita.ontimepro.data.CourseDatabase
import com.gdsciiita.ontimepro.network.ClassroomApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

enum class ClassroomApiStatus { LOADING, ERROR, DONE }


class MainViewModel(private val courseDao: CourseDao) : ViewModel() {

    fun allCourses() : Flow<List<Course>> = courseDao.getCourses()

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<ClassroomApiStatus>()
    private val _courses = MutableLiveData<List<Course>>()

    private val _courseWorks=MutableLiveData<List<Assignment>>()
    private val _error = MutableLiveData<String>()


    // The external immutable LiveData for the request status
    val status: LiveData<ClassroomApiStatus> = _status
    val courses: LiveData<List<Course>> = _courses

    val courseWorks: LiveData<List<Assignment>> = _courseWorks
    val error: LiveData<String> = _error


    /**
     * Gets Classroom courses information from the Classroom API Retrofit service and updates the
     * [Course] [List] [LiveData].
     */
    fun getClassroomCourses() {
        //coroutine scope for viewModel
        viewModelScope.launch {
            _status.value = ClassroomApiStatus.LOADING
            try {
                Log.d(TAG, "GETTING COURSES")
                val courseList =  ClassroomApi.retrofitService
                    .getCourses(User.authToken, "ACTIVE", 10, User.userEmail).courseList
                //TODO: REMOVE TEST DATA
                for(course in courseList) {
                    course.facultyName = "Mohammed Javed"
                    course.classType = "Lecture"
                }
                _courses.value = courseList
                _status.value = ClassroomApiStatus.DONE

                viewModelScope.launch {
                    courseDao.addCourses(courseList)
                }

            } catch (e: Exception) {
                _error.value = e.message
                e.message?.let { Log.e("WRONG", it) }
                _status.value = ClassroomApiStatus.ERROR
                _courses.value = listOf()
            }
        }
    }

    fun getClassroomCourseWork(){
        viewModelScope.launch {
            _status.value = ClassroomApiStatus.LOADING
            try{
                Log.d(TAG,"GETTING COURSEWORK")
                //TODO: Replace courseId with your course
                val assignmentList=ClassroomApi.retrofitService
                    .getCourseWork(User.authToken, "251218975786","PUBLISHED",10).assignmentList

                _courseWorks.value = assignmentList
                _status.value = ClassroomApiStatus.DONE
            } catch (e:Exception){
                e.message?.let { Log.e("WRONG", it) }
                _status.value = ClassroomApiStatus.ERROR
                _courseWorks.value = listOf()
            }
        }
    }

}
class CourseViewModelFactory(private val courseDao: CourseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(courseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}