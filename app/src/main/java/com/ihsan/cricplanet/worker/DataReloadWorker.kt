package com.ihsan.cricplanet.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class DataReloadWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        Log.d("newsWorker", "doWork: Worker Started")
        return try {
            //autoReload()
            Log.d("newsWorker", "doWork: Get Article Called")
            Result.success()
        } catch (e: Exception) {
            Log.d("newsWorker", "doWork: $e")
            Result.failure()
        }
    }
}