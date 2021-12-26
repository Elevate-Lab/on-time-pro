package com.gdsciiita.ontimepro.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gdsciiita.ontimepro.R
import com.gdsciiita.ontimepro.classes.Course
import java.util.ArrayList

class course_adapter(private val context: Context, private val listener:course_adapter):RecyclerView.Adapter<course_adapter.MyViewHolder>() {

    private var courses=ArrayList<Course>()

    inner class MyViewHolder(itemview: View):RecyclerView.ViewHolder(itemview)
    {
        val courseName: TextView =itemView.findViewById(R.id.courseName)
        val facultyName: TextView =itemView.findViewById(R.id.facultyName)
        val classType: TextView =itemView.findViewById(R.id.classType)
        val chatroom: Button=itemview.findViewById(R.id.chatroom)
        val joinMeet: Button=itemview.findViewById(R.id.joinMeet)
        val preview: Button=itemview.findViewById(R.id.preview)
        val deleteButton: ImageView =itemview.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewHolder= MyViewHolder(LayoutInflater.from(context).inflate(R.layout.course_item,parent,false))
        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(courses[viewHolder.adapterPosition])
        }
        viewHolder.preview.setOnClickListener {
            listener.onItemClicked(courses[viewHolder.adapterPosition])
        }
        viewHolder.joinMeet.setOnClickListener {
            listener.onItemClicked(courses[viewHolder.adapterPosition])
        }
        viewHolder.chatroom.setOnClickListener {
            listener.onItemClicked(courses[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    private fun onItemClicked(course: Course) {

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentCourse=courses[position]
        holder.courseName.text=currentCourse.getCourseName()
        holder.facultyName.text=currentCourse.getFacultyName()
        holder.classType.text=currentCourse.getClassType()
    }

    override fun getItemCount(): Int {
        return courses.size
    }

}