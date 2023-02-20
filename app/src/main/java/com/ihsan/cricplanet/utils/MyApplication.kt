package com.ihsan.cricplanet.utils

import android.annotation.SuppressLint
import android.app.Application

@SuppressLint("StaticFieldLeak")
class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}