package com.example.on_time_pro.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.on_time_pro.R
import com.example.on_time_pro.classes.Assignment
import java.util.ArrayList

class assignment_adapter(private val context: Context, private val listener:assignment_adapter):
    RecyclerView.Adapter<assignment_adapter.MyViewHolder>() {

    private var assignments= ArrayList<Assignment>()

    inner class MyViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {
        val date: TextView =itemView.findViewById(R.id.date)
        val subject: TextView =itemView.findViewById(R.id.subject)
        val submissionDay: TextView =itemView.findViewById(R.id.submissionDay)
        val submissionTime: TextView =itemview.findViewById(R.id.submissionTime)
        val status: TextView =itemview.findViewById(R.id.status)
        val pointsReceived: TextView =itemview.findViewById(R.id.pointsReceived)
        val maxPoints: TextView =itemview.findViewById(R.id.maxPoints)
        val addAssign: Button =itemview.findViewById(R.id.addAssign)
        val markDone: Button =itemview.findViewById(R.id.markDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): assignment_adapter.MyViewHolder {
        val viewHolder= MyViewHolder(LayoutInflater.from(context).inflate(R.layout.assignment_item,parent,false))

        viewHolder.addAssign.setOnClickListener {
            listener.onItemClicked(assignments[viewHolder.adapterPosition])
        }
        viewHolder.markDone.setOnClickListener {
            listener.onItemClicked(assignments[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    private fun onItemClicked(assignment: Assignment) {

    }

    override fun onBindViewHolder(holder: assignment_adapter.MyViewHolder, position: Int) {
        val current=assignments[position]
        holder.date.text=current.getDate()
        holder.status.text=current.getStatus()
        holder.subject.text=current.getSubject()
        holder.submissionDay.text=current.getSubmissionDay()
        holder.submissionTime.text=current.getSubmissionTime()
        holder.maxPoints.setText(current.getMaxPoints())
        holder.pointsReceived.setText(current.getPointsReceived())
    }

    override fun getItemCount(): Int {
        return assignments.size
    }

}