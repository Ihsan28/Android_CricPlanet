package com.ihsan.cricplanet.ui

import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ihsan.cricplanet.R.id
import com.ihsan.cricplanet.databinding.ActivityMainBinding
import com.ihsan.cricplanet.ui.fragment.HomeFragment
import com.ihsan.cricplanet.utils.CheckNetwork
import com.ihsan.cricplanet.utils.WorkRequest

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavigation: BottomNavigationView
    private val checkNetwork=CheckNetwork()
    override fun onResume() {
        super.onResume()
        //Network check register and toast at start up
        registerReceiver(checkNetwork, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
        CheckNetwork.networkStatus.observe(this) {
            if (!it.wifi && !it.cellular && !it.ethernet) {
                Toast.makeText(this, "No Internet.....", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Internet Connected home", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

    override fun onPause() {
        super.onPause()
        //Network check unregister
        unregisterReceiver(checkNetwork)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}