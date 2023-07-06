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
import com.ihsan.cricplanet.utils.CheckNetwork
import com.ihsan.cricplanet.utils.CheckNetwork.Companion.networkStatus
import com.ihsan.cricplanet.utils.Network
import com.ihsan.cricplanet.utils.SignalingNetworkListener
import com.ihsan.cricplanet.utils.WorkRequest

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var checkNetwork: CheckNetwork


    override fun onResume() {
        super.onResume()
        checkNetwork = CheckNetwork(createNetworkListener())
        //check Internet permission
        checkNetwork.checkINTERNETPermission()
        //Network check register and toast at start up
        registerReceiver(checkNetwork, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
        networkStatus.observe(this) {
            val networkStatusMessage = if (!it.connection) {
                "No Internet Connection"
            } else {
                if (it.ethernet) {
                    "Ethernet Connected"
                } else if (it.wifi) {
                    "Wifi Connected"
                } else if (it.cellular) {
                    "Cellular Connected"
                }else{
                    "No Internet Connected"
                }
            }
            Toast.makeText(this, networkStatusMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun createNetworkListener() = object : SignalingNetworkListener {
        override fun onConnectionEstablished(network: Network) {
            // alternative to the networkStatus LiveData
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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