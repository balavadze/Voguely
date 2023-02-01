package com.wit.myapplication.ui.loginSignOut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wit.myapplication.R
import com.wit.myapplication.databinding.ToolbarBinding



public class ToolbarFragment : Fragment() {
    lateinit var binding: ToolbarBinding
    private lateinit var viewModel: SignOutViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = ToolbarBinding.inflate(inflater, container, false)
        binding.tvSignOut.setOnClickListener {
            // viewModel.signOut()
        }
        return binding.root
    }

    private fun goToLogInScreen() {
        findNavController().navigate(R.id.action_mainFragment_to_LogInFragment)
    }

    private fun setEvent(event: SignOutEvent) {
        when (event) {
            is SignOutEvent.SignOutSuccess -> goToLogInScreen()
            is SignOutEvent.SignOutError -> Toast.makeText(
                requireContext(),
                event.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}