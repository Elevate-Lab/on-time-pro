package com.gdsciiita.ontimepro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import com.gdsciiita.ontimepro.BaseApplication
import com.gdsciiita.ontimepro.adapters.CourseAdapter
import com.gdsciiita.ontimepro.databinding.FragmentClassroomBinding
import com.gdsciiita.ontimepro.viewModels.CourseViewModelFactory
import com.gdsciiita.ontimepro.viewModels.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ClassroomFragment : Fragment() {

    private var _binding: FragmentClassroomBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels {
        CourseViewModelFactory(
            (activity?.application as BaseApplication).database.courseDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentClassroomBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val courseAdapter = CourseAdapter{}

        lifecycle.coroutineScope.launch {
            viewModel.allCourses().collect {
                courseAdapter.submitList(it)
            }
        }
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        //setup adapter
        binding.recyclerView.adapter = CourseAdapter{}

        viewModel.getClassroomCourses()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}