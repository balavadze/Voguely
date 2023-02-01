package com.wit.myapplication.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.wit.myapplication.R
import com.wit.myapplication.databinding.FragmentMainBinding


@Suppress("DEPRECATION")
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.tv_sign_out -> {
                // signOutFromApp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bottomNavigationView.setupWithNavController(navController)
    }

/*    private fun signOutFromApp() {
        val task = FirebaseAuth.getInstance().signOut()
        task.addOnSuccessListener {
            Toast.makeText(activity, "Now you are signed out.", Toast.LENGTH_SHORT).show()
            val navController = findNavController()
            navController.navigate(R.id.action_mainFragment_to_LogInFragment)
        }
        task.addOnFailureListener {
            Toast.makeText(activity, "Sign out failed.", Toast.LENGTH_SHORT).show()
        }
    }*/

}
