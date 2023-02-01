package com.wit.myapplication.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wit.myapplication.R
import com.wit.myapplication.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

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
        binding.logo.animate()
            .alpha(1F)
            .setDuration(3000)
            .withEndAction {
                val user = Firebase.auth.currentUser
                if (user == null) {
                    findNavController().navigate(R.id.action_splashFragment_to_logInFragment)
                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
                }
            }
    }
}
