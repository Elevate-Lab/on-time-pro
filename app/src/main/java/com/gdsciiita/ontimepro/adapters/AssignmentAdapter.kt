package com.gdsciiita.ontimepro.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gdsciiita.ontimepro.classes.Assignment
import com.gdsciiita.ontimepro.databinding.AssignmentItemBinding

class AssignmentAdapter: ListAdapter<Assignment, AssignmentAdapter.AssignmentViewHolder>(AssignmentAdapter) {

    companion object DiffCallback : DiffUtil.ItemCallback<Assignment>() {
        override fun areItemsTheSame(oldItem: Assignment, newItem: Assignment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Assignment, newItem: Assignment): Boolean {
            return oldItem.workType == newItem.workType
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        return AssignmentViewHolder(AssignmentItemBinding.inflate(LayoutInflater.from(parent.context)))

    }

    class AssignmentViewHolder(private var binding:AssignmentItemBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(Assignment: Assignment) {
            //binds each assignment to layout
            binding.assignment = Assignment
            binding.executePendingBindings() //update views
        }}

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        val assignment = getItem(position)
        holder.bind(assignment)
    }

}