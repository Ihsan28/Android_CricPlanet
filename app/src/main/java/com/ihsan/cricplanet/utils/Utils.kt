package com.ihsan.cricplanet.utils

import android.util.Log
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Utils {
    fun refreshMessage() {
        Toast.makeText(MyApplication.instance, "Reloading News", Toast.LENGTH_SHORT).show()
    }

    fun logNtoast(tag: String, message: String) {
        Toast.makeText(MyApplication.instance, "tag: $tag message: $message", Toast.LENGTH_SHORT)
            .show()
        Log.d(tag, message)
    }

    fun upcomingYearDuration():String {

        // Get the current date and time
        val currentDateTime = LocalDateTime.now()

        // Add 1 year to the current date and time
        val nextYearDateTime = currentDateTime.plusYears(1)

        // Format the new date and time as a string
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val nextMinuteDateTimeString = currentDateTime.format(formatter)
        val nextYearDateTimeString = nextYearDateTime.format(formatter)

        //Combine them in one string using comma as separator
        val upcomingYearString="$nextMinuteDateTimeString,$nextYearDateTimeString"
        Log.d("cricUtils", "upcomingYearDuration: $upcomingYearString")

        return upcomingYearString
    }

    fun recentMonthDuration():String {

        // Get the current date and time
        val currentDateTime = LocalDateTime.now()

        // Add 1 year to the current date and time
        val lastMonthsDateTime = currentDateTime.minusMonths(2)

        // Format the new date and time as a string
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val lastMinuteDateTimeString = currentDateTime.format(formatter)
        val lastMonthsDateTimeString = lastMonthsDateTime.format(formatter)

        //Combine them in one string using comma as separator
        val upcomingYearString="$lastMonthsDateTimeString,$lastMinuteDateTimeString"
        Log.d("cricUtils", "lastMonthDuration: $upcomingYearString")

        return upcomingYearString
    }

    fun getCurrentDate(): String {
        // Get the current date and time
        val currentDateTime = LocalDateTime.now()
        val tomorrowDateTime = currentDateTime.plusDays(1)
        // Format the new date as a string
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yy")
        Log.d("cricUtils", "currentDateTime: $currentDateTime")
        return "${currentDateTime.format(formatter)},${tomorrowDateTime.format(formatter)}"
    }

    fun dateFormat(dateString: String): List<String> {
        val apiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'000Z'")
        val targetFormat = DateTimeFormatter.ofPattern("dd-MM-yy/hh:mm a")
        val date = apiFormat.parse(dateString)
        val arrayDateTime=targetFormat.format(date).split("/")
        return arrayDateTime
    }

    fun timeAgoConverter(timestamp: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        val date = dateFormat.parse(timestamp)
        val currentTime = Calendar.getInstance().timeInMillis
        var hoursAgo = (currentTime - date.time) / (60 * 60 * 1000)
        var yMDHAgo = ""
        if (hoursAgo.equals(0)) {
            return "1h> ago"
        }
        if (hoursAgo >= 365 * 24) {
            yMDHAgo += " ${hoursAgo / 365 * 24}y"
            hoursAgo %= 365 * 24
        }
        if (hoursAgo >= 30 * 24) {
            yMDHAgo += " ${hoursAgo / 30 * 24}m"
            hoursAgo %= 30 * 24
        }
        if (hoursAgo >= 24) {
            yMDHAgo += " ${hoursAgo / 24}d"
            hoursAgo %= 24
        }
        if (hoursAgo > 0) {
            yMDHAgo += " ${hoursAgo}h"
        }
        return "$yMDHAgo ago"
    }
}