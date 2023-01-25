package com.wit.myapplication.ui.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.wit.myapplication.R
import com.wit.myapplication.databinding.FragmentLogInBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding
    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {

            viewModel.selectedTab.collectLatest { selectedTab -> setSelectedTabText(selectedTab) }

        }
        lifecycleScope.launch {
            viewModel._event.collectLatest { event -> setEvent(event) }
        }

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(selectedTab: TabLayout.Tab) {
                val isLoginTabSelected = binding.tabLayout.getTabAt(0)?.isSelected ?: false
                viewModel.onSelectedTabChanged(if (isLoginTabSelected) SelectedTab.LOGIN else SelectedTab.SIGN_UP)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })

        binding.logInButton.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            viewModel.onButtonClick(email, password)
        }

    }

    private fun goToHomeScreen() {
        findNavController().navigate(R.id.action_logInFragment_to_mainFragment)
    }

    private fun setEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LogInSuccess -> goToHomeScreen()
            is LoginEvent.LoginError -> Toast.makeText(
                requireContext(),
                event.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setSelectedTabText(selectedTab: SelectedTab) {
        binding.welcome.setText(if (selectedTab == SelectedTab.LOGIN) R.string.welcome_back else R.string.join)
        binding.logInButton.setText(if (selectedTab == SelectedTab.SIGN_UP) R.string.sign_up else R.string.login)
    }
}


///


//TODO in case you need to enter app without authentication - findNavController().navigate(R.id.action_logInFragment_to_mainFragment)


// val isLoginTabSelected = binding.tabLayout.getTabAt(0)?.isSelected ?: false

/* if (isLoginTabSelected) {
     viewModel.logIn(email, password)
         .addOnCompleteListener { task ->
             if (task.isSuccessful) {
                 //TODO here navigation component but, user specified ???
                 findNavController().navigate(R.id.action_logInFragment_to_mainFragment)
             }
             else {
                 // TODO SOME MESSAGE OR SCREEN TRY AGAIN
             }
         }

 } else {
     viewModel.signUp(email, password)
 }*/

/*if (isLoginTabSelected) {
           auth.signInWithEmailAndPassword(email, password)
               .addOnCompleteListener { task ->
                   if (task.isSuccessful) {
                       //TODO here navigation component but, user specified ???
                       findNavController().navigate(R.id.action_logInFragment_to_mainFragment)
                   }
                   else {
                       // TODO SOME MESSAGE OR SCREEN TRY AGAIN
                   }
               }
       }
       else {
           auth.createUserWithEmailAndPassword(email, password)
               .addOnCompleteListener { task ->
                   if (task.isSuccessful) {
                       //TODO UI TO MAIN SCREEN
                       findNavController().navigate(R.id.action_logInFragment_to_mainFragment)
                   }
                   else {

                   }
               }
       }*/
