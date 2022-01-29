package com.gdsciiita.ontimepro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.gdsciiita.ontimepro.BaseApplication
import com.gdsciiita.ontimepro.adapters.AssignmentAdapter
import com.gdsciiita.ontimepro.databinding.FragmentAssignmentsBinding
import com.gdsciiita.ontimepro.viewModels.CourseViewModelFactory
import com.gdsciiita.ontimepro.viewModels.MainViewModel

class AssignmentsFragment : Fragment() {

    private val viewModel1: MainViewModel by activityViewModels {
        CourseViewModelFactory(
            (activity?.application as BaseApplication).database.courseDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel1.getClassroomCourseWork(viewModel1.courses.value)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val binding = FragmentAssignmentsBinding.inflate(inflater)

        val assignmentAdapter = AssignmentAdapter{}

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel1

        //setup adapter
        binding.recyclerView.adapter = assignmentAdapter

        return binding.root

    }

}