package com.wit.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.wit.myapplication.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = binding.tabLayout

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                var loginTab = binding.tabLayout.getTabAt(0)
                var signUpTab = binding.tabLayout.getTabAt(1)
                when {
                    signUpTab?.isSelected == true -> {
                        binding.logInButton.text = getString(R.string.signUpButtonText)
                        binding.welcome.text = getString(R.string.join)
                    }
                    loginTab?.isSelected == true -> {
                        binding.logInButton.text = getString(R.string.loginButtonText)
                        binding.welcome.text = getString(R.string.welcome_back)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // do something when tab is unselected
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // do something when tab is reselected
            }
        })
    }
}
