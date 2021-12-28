package com.gdsciiita.ontimepro.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gdsciiita.ontimepro.classes.Course
import com.gdsciiita.ontimepro.databinding.CourseItemBinding

class CourseAdapter : ListAdapter<Course, CourseAdapter.CourseViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.classType == newItem.classType
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(CourseItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = getItem(position)
        holder.bind(course)
    }

    class CourseViewHolder(private var binding:
                              CourseItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(Course: Course) {
            //binds each course to layout
            binding.course = Course
            binding.executePendingBindings() //update views
        }
    }

}