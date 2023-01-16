package com.wit.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wit.myapplication.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logo.animate().alpha(1F).setDuration(3000)
            .withEndAction {
                val action = (R.id.action_splashFragment_to_logInFragment)
                findNavController().navigate(action)
            }
        // .withEndAction. { R.id.action_splashFragment_to_logInFragment }
        //findNavController().navigate(R.id.action_splashFragment_to_logInFragment)
        // view.findViewById<RecyclerView>(R.id.photos_grid).setOnClickListener {}
    }
}
