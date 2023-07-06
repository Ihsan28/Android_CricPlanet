package com.ihsan.cricplanet.utils

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.ihsan.cricplanet.ui.MainActivity
import com.ihsan.cricplanet.ui.fragment.HomeFragment
import com.ihsan.cricplanet.viewmodel.CricViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.lang.Thread.sleep
import kotlin.coroutines.CoroutineContext

data class Network(
    var connection: Boolean,
    var wifi: Boolean,
    var cellular: Boolean,
    var ethernet: Boolean
)

class CheckNetwork(private val listener: SignalingNetworkListener) : BroadcastReceiver() {
    companion object {
        val networkStatus = MutableLiveData(
            Network(
                connection = false, wifi = false, cellular = false, ethernet = false
            )
        )
    }

    override fun onReceive(context: Context, intent: Intent) {
        Thread {
            sleep(1000)
        }
        val network=Network(
            connection = false, wifi = false, cellular = false, ethernet = false
        )
        val instance = MyApplication.instance
        val connectivityManager =
            instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        Log.d("Internet", "onReceive: $capabilities")
        if (capabilities != null) {
            if (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)){
                Log.i("Internet", "NetworkCapabilities.NET_CAPABILITY_INTERNET")
                Toast.makeText(instance, "INTERNET Connected", Toast.LENGTH_SHORT)
                    .show()
                network.connection = true
            } else {
                network.connection = false
            }

            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                Toast.makeText(instance, "ETHERNET ON", Toast.LENGTH_SHORT)
                    .show()
                network.ethernet = true
            } else {
                network.ethernet = false
            }

            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                Toast.makeText(instance, "WIFI ON", Toast.LENGTH_SHORT).show()
                network.wifi = true
                network.cellular = false
            } else {
                network.wifi = false
            }

            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                Toast.makeText(instance, "CELLULAR ON", Toast.LENGTH_SHORT)
                    .show()
                network.cellular = true
            } else {
                network.cellular = false
            }


        } else {
            Toast.makeText(MyApplication.instance, "No Internet", Toast.LENGTH_SHORT)
                .show()
            Log.d("Internet", "onReceive: Not connected")
            network.connection = false
        }
        networkStatus.postValue(network)

        // Alternative of companion object
        listener.onConnectionEstablished(network)
    }

    fun checkINTERNETPermission() {
        val permission = Manifest.permission.INTERNET
        if (ContextCompat.checkSelfPermission(
                MyApplication.instance, permission
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                MainActivity(),
                arrayOf(permission),
                Constant.internetPermissionAccessCode
            )
        }
    }
}