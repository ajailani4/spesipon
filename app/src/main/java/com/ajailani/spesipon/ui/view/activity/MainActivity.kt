package com.ajailani.spesipon.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ajailani.spesipon.NavGraphDirections
import com.ajailani.spesipon.R
import com.ajailani.spesipon.databinding.ActivityMainBinding
import com.ajailani.spesipon.ui.view.fragment.BrandsFragment
import com.ajailani.spesipon.ui.view.fragment.HomeFragment
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // NavHost Fragment as Fragment Container
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        // NavController is for controlling the navigation
        navController = navHostFragment.findNavController()

        // Get current fragment
        currentFragment = navHostFragment.childFragmentManager.fragments.first()

        binding.bottomNav.setupWithNavController(navController)

        // When phoneSearchFab is clicked
        binding.phoneSearchFab.setOnClickListener {
            navigateToPhoneSearch()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    private fun navigateToPhoneSearch() {
        // Setup transition animation to PhoneSearchFragment
        currentFragment.apply {
            exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
                duration = 300
            }

            reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
                duration = 300
            }
        }

        val direction = NavGraphDirections.actionGlobalPhoneSearchFragment()
        navController.navigate(direction)
    }
}