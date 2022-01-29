package com.gdsciiita.ontimepro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

    private val viewModel1: MainViewModel by activityViewModels {
        CourseViewModelFactory(
            (activity?.application as BaseApplication).database.courseDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel1.getClassroomCourses()
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

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.apply {
            // Giving the binding access to the OverviewViewModel
            viewModel = viewModel1

            //setup adapter
            recyclerView.adapter = courseAdapter

            swipeLayout.setOnRefreshListener {
                viewModel1.getClassroomCourses()
                swipeLayout.isRefreshing = false;
            }
        }

        viewModel1.allCourses().observe(this.viewLifecycleOwner) { items ->
            items.let {
                courseAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}