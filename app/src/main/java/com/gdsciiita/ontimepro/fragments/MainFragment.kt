package com.gdsciiita.ontimepro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gdsciiita.ontimepro.R

class MainFragment : Fragment(),View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
    var navC: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navC= Navigation.findNavController(view)
        view.findViewById<Button>(R.id.getStarted)?.setOnClickListener(this)
    }

    override fun onClick(v:View?){
        navC?.navigate(R.id.action_mainFragment_to_classroom)
    }
}