package com.ihsan.cricplanet.utils

import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.ihsan.cricplanet.worker.DataReloadWorker
import java.util.concurrent.TimeUnit

private const val TAG = "cricWorkRequest"
class WorkRequest {
    fun setPeriodicWorkRequest() {
        val workManager = WorkManager.getInstance(MyApplication.instance)
        Log.d(TAG, "setPeriodicWorkRequest: Entered into worker for home refresh")
        val dataLoad = PeriodicWorkRequest
            .Builder(DataReloadWorker::class.java, 15, TimeUnit.MINUTES)
            .setInitialDelay(1, TimeUnit.MINUTES)
            .addTag("ReloadData")
            .build()
        workManager.enqueueUniquePeriodicWork(
            "ReloadData",
            ExistingPeriodicWorkPolicy.REPLACE,
            dataLoad
        )
    }
}