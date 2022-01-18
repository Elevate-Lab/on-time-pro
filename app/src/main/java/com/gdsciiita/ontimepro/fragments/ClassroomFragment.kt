package com.gdsciiita.ontimepro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.gdsciiita.ontimepro.BaseApplication
import com.gdsciiita.ontimepro.adapters.CourseAdapter
import com.gdsciiita.ontimepro.databinding.FragmentClassroomBinding
import com.gdsciiita.ontimepro.viewModels.CourseViewModelFactory
import com.gdsciiita.ontimepro.viewModels.MainViewModel

class ClassroomFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels {
        CourseViewModelFactory(
            (activity?.application as BaseApplication).database.courseDao()
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                savedInstanceState: Bundle?): View? {

        val binding = FragmentClassroomBinding.inflate(inflater)


        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        //setup adapter
        binding.recyclerView.adapter = CourseAdapter()

        //API CALL
        viewModel.getClassroomCourses()

        return binding.root
    }
}