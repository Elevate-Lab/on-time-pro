package com.gdsciiita.ontimepro.bindingAdapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gdsciiita.ontimepro.R
import com.gdsciiita.ontimepro.adapters.CourseAdapter
import com.gdsciiita.ontimepro.classes.Course
import com.gdsciiita.ontimepro.viewModels.ClassroomApiStatus

//Binding Adapters are annotated methods used to create custom setters for custom properties of your view.


//bind to recyclerView in fragment_overview
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Course>?) {
    val adapter = recyclerView.adapter as CourseAdapter
    adapter.submitList(data)
}



//bind to status imageView in fragment_overview
@BindingAdapter("classroomApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: ClassroomApiStatus?) {
    when (status) {
        ClassroomApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        ClassroomApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        ClassroomApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}