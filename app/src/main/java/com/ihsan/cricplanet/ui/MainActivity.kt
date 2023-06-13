package com.ihsan.cricplanet.ui

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ihsan.cricplanet.R.id
import com.ihsan.cricplanet.databinding.ActivityMainBinding
import com.ihsan.cricplanet.utils.CheckNetwork
import com.ihsan.cricplanet.utils.WorkRequest

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var checkNetwork: CheckNetwork

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkNetwork = CheckNetwork()

        //Network check register and toast at start up
        registerReceiver(checkNetwork, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
        //check Internet permission
        checkNetwork.checkINTERNETPermission()
        //Periodic work request call
        WorkRequest().setPeriodicWorkRequest()

        bottomNavigation = binding.bottomNav

        val navHostFragment =
            supportFragmentManager.findFragmentById(id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigation.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}