package com.gdsciiita.ontimepro.network

import com.gdsciiita.ontimepro.classes.AssignmentResponse
import com.gdsciiita.ontimepro.classes.CourseResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

private val BASE_URL =
    "https://classroom.googleapis.com"

//moshi object for JSON conversion
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//retrofit object for API calls
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//define how retrofit will talk to api service
interface ClassroomApiService {
    @GET("/v1/courses")
    suspend fun getCourses(@Header("Authorization") authToken: String,
                           @Query("courseStates") courseState: String,
                           @Query("pageSize") pageSize: Int,
                           @Query("studentId") email: String): CourseResponse


    @GET("/v1/courses/{courseId}/courseWork")
    suspend fun getCourseWork(@Header("Authorization") authToken: String,
                              @Path("courseId") courseId: String,
                              @Query("courseWorkStates") courseWorkState: String,
                              @Query("pageSize") pageSize: Int) : AssignmentResponse
}

//singleton object declaration
object ClassroomApi {
    val retrofitService : ClassroomApiService by lazy {
        retrofit.create(ClassroomApiService::class.java)
    }
}
