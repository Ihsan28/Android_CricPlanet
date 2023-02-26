package com.ihsan.cricplanet.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ihsan.cricplanet.ui.fragment.HomeFragment

class DataReloadWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        Log.d("cricWorker", "doWork: Worker Started")
        return try {
            HomeFragment().autoReload()
            Log.d("cricWorker", "doWork: Get Cricket information Api Called")
            Result.success()
        } catch (e: Exception) {
            Log.d("cricWorker", "doWork: $e")
            Result.failure()
        }
    }
}