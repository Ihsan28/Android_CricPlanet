package com.ihsan.cricplanet.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ihsan.cricplanet.R.id
import com.ihsan.cricplanet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavigation: BottomNavigationView
    //private lateinit var checkNetwork:CheckNetwork

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigation = binding.bottomNav
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                id.homeFragment,
                id.matchTabLayoutFragment,
                id.seriesFragment,
                id.playerFragment,
                id.rankingTabLayoutFragment
            )
        )
        val navHostFragment =
            supportFragmentManager.findFragmentById(id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        //setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigation.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}