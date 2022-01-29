package com.gdsciiita.ontimepro.bindingAdapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gdsciiita.ontimepro.R
import com.gdsciiita.ontimepro.adapters.AssignmentAdapter
import com.gdsciiita.ontimepro.adapters.CourseAdapter
import com.gdsciiita.ontimepro.classes.Assignment
import com.gdsciiita.ontimepro.classes.Course
import com.gdsciiita.ontimepro.classes.DueDate
import com.gdsciiita.ontimepro.classes.DueTime
import com.gdsciiita.ontimepro.viewModels.ClassroomApiStatus
import java.text.SimpleDateFormat
import java.util.*

//Binding Adapters are annotated methods used to create custom setters for custom properties of your view.



@BindingAdapter("list")
fun bindingRecyclerView(recyclerView: RecyclerView,
                     data: List<Assignment>?) {
    val adapter = recyclerView.adapter as AssignmentAdapter
    adapter.submitList(data)
}

@BindingAdapter("bind:date", "bind:time")
fun bindDate(textView: TextView,
                        data: DueDate?, time: DueTime?) {
//    val inputFormat = SimpleDateFormat("d-MM-yyyy HH:mm", Locale.getDefault())
//    val outputFormat = SimpleDateFormat("d MMM yyyy h:mm a", Locale.getDefault())
//    val date = inputFormat.parse("${data.day}-${data.month}-${data.year} ${time.hours}:${time.minutes}")
//    val textDate = outputFormat.format(date)
    if(data == null || time == null)
        textView.text = "No due date"
    else
        textView.text = "Due ${data.day}-${data.month}-${data.year} ${time.hours}:${time.minutes}"
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