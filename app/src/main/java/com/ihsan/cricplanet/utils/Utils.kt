package com.ihsan.cricplanet.utils

import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.MatchAdapterHome
import com.ihsan.cricplanet.model.Venue
import com.ihsan.cricplanet.model.VenueIncludeCountry
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.round

class Utils {

    fun upcomingYearDuration(): String {

        // Get the current date and time
        val currentDateTime = LocalDateTime.now()

        // Add 1 year to the current date and time
        val nextYearDateTime = currentDateTime.plusYears(1)

        // Format the new date and time as a string
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val nextMinuteDateTimeString = currentDateTime.format(formatter)
        val nextYearDateTimeString = nextYearDateTime.format(formatter)

        //Combine them in one string using comma as separator
        val upcomingYearString = "$nextMinuteDateTimeString,$nextYearDateTimeString"
        Log.d("cricUtils", "upcomingYearDuration: $upcomingYearString")

        return upcomingYearString
    }

    fun recentMonthDuration(): String {

        // Get the current date and time
        val currentDateTime = LocalDateTime.now()

        // Add 1 year to the current date and time
        val lastMonthsDateTime = currentDateTime.minusMonths(2)

        // Format the new date and time as a string
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val lastMinuteDateTimeString = currentDateTime.format(formatter)
        val lastMonthsDateTimeString = lastMonthsDateTime.format(formatter)

        //Combine them in one string using comma as separator
        val upcomingYearString = "$lastMonthsDateTimeString,$lastMinuteDateTimeString"
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
        val arrayDateTime = targetFormat.format(date).split("/")
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

    fun getAge(date: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        val date = dateFormat.parse(date)
        val currentTime = Calendar.getInstance().timeInMillis
        var days = (currentTime - date.time) / (60 * 60 * 24 * 1000)

        if (days >= 365) {
            return "${(days / 365 * 24).toInt()}"
        } else {
            return ""
        }
    }

    fun setStatus(
        status: String?,
        live: Boolean?,
        statusTextView: TextView
    ) {
        if (status == "Finished") {
            statusTextView.setBackgroundColor(
                ContextCompat.getColor(
                    MyApplication.instance, R.color.md_blue_grey_700
                )
            )
            statusTextView.text = status
        } else {
            if (status == "NS") {
                statusTextView.text = "UPCOMING"
                statusTextView.setBackgroundColor(
                    ContextCompat.getColor(
                        MyApplication.instance, R.color.md_yellow_700
                    )
                )
            } else {

                if (status == "Postp.") {
                    statusTextView.text = "POSTPONED"
                    statusTextView.setBackgroundColor(
                        ContextCompat.getColor(
                            MyApplication.instance, R.color.md_yellow_900
                        )
                    )
                } else if (status == "Aban.") {
                    statusTextView.text = "ABANDONED"
                    statusTextView.setBackgroundColor(
                        ContextCompat.getColor(
                            MyApplication.instance, R.color.md_red_200
                        )
                    )
                } else if (live == true) {
                    statusTextView.text = "• LIVE"
                    statusTextView.setBackgroundColor(
                        ContextCompat.getColor(
                            MyApplication.instance, R.color.md_red_400
                        )
                    )
                }
            }
        }
    }

    fun setVenue(
        status: String?,
        note: String?,
        venue: VenueIncludeCountry?,
        noteOrVenue:TextView
    ) {
        if (status == "Finished") {
            noteOrVenue.text = note
        } else {
            if (venue?.name == null || venue.city == null) {
                "Not Decided Yet".also { noteOrVenue.text = it }
            } else {
                if (venue.country?.name != null) {
                    noteOrVenue.text =
                        "${venue.name} • ${venue.city} • ${venue.country?.name}"
                }else{
                    noteOrVenue.text = "${venue.name} • ${venue.city}"
                }
            }
        }
    }

    fun loadImageWithPicasso(imagePath:String?,imageView: ImageView){
        if (!TextUtils.isEmpty(imagePath)) {
            Picasso.get().load(imagePath).fit()
                .placeholder(R.drawable.progress_animation).into(imageView)
        } else {
            imageView.setImageResource(R.drawable.ic_image)
        }
    }

}