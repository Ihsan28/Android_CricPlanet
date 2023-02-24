package com.ihsan.cricplanet.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.Team
import com.ihsan.cricplanet.model.VenueIncludeCountry
import com.ihsan.cricplanet.model.fixture.scoreboard.run.RunWithTeam
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import android.content.Context


class Utils {

    fun snackBar(parentLayout: View) {
        val snackbar = Snackbar.make(parentLayout, "Not available right now", Snackbar.LENGTH_LONG)
        snackbar.setTextColor(ContextCompat.getColor(MyApplication.instance, R.color.white))
        snackbar.setBackgroundTint(ContextCompat.getColor(MyApplication.instance, R.color.teal_700))
        snackbar.setActionTextColor(ContextCompat.getColor(MyApplication.instance, R.color.white))
        snackbar.setAction("Dismiss") { snackbar.dismiss() }
        snackbar.show()
    }

    fun showStyledSnackbar(view: View, text: String?) {
        var showText = ""
        if (text != null) {
            showText = text
        }
        val snackbar = Snackbar.make(view, showText, Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.parseColor("#3F51B5"))
        val textView =
            snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(Color.WHITE)
        textView.textSize = 20f
        textView.setTypeface(null, Typeface.BOLD)
        snackbar.show()
    }

    fun progressAnimation(context:Context): Dialog {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.progress_bar)
        dialog.setCancelable(false)

        val progressBar = dialog.findViewById<ProgressBar>(R.id.progress_bar)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window?.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        progressBar.layoutParams=lp
        return dialog
    }

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
        val formatter = DateTimeFormatter.ofPattern("yy-MM-dd")
        Log.d(
            "cricUtils",
            "currentDateTime: ${currentDateTime.format(formatter)},${
                tomorrowDateTime.format(formatter)
            }"
        )
        return "${currentDateTime.format(formatter)},${tomorrowDateTime.format(formatter)}"
    }

    fun dateFormat(dateString: String): List<String> {
        val apiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'000Z'")
        val targetFormat = DateTimeFormatter.ofPattern("dd-MM-yy/hh:mm a")
        val date = apiFormat.parse(dateString)
        val arrayDateTime = targetFormat.format(date).split("/")
        return arrayDateTime
    }

    fun getPlayerBorn(dateString: String): String {
        val apiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val targetFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
        val date = apiFormat.parse(dateString)
        return "${targetFormat.format(date)} (${getPlayerAge(dateString)})"
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

    fun getPlayerAge(date: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(date)
        val currentTime = Calendar.getInstance().timeInMillis

        var days = (currentTime - date.time) / (60 * 60 * 24 * 1000)

        if (days >= 365) {
            return "${(days / 365).toInt()}"
        } else {
            return ""
        }
    }

    @SuppressLint("SetTextI18n")
    fun setStatus(
        status: String?,
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
                val liveStatus = listOf(
                    "1st Innings",
                    "2nd Innings",
                    "3rd Innings",
                    "4th Innings",
                    "Stump Day 1",
                    "Stump Day 2",
                    "Stump Day 3",
                    "Stump Day 4",
                    "Innings Break"
                )

                if (status == "Postp.") {
                    statusTextView.text = "POSTPONED"
                    statusTextView.setBackgroundColor(
                        ContextCompat.getColor(
                            MyApplication.instance, R.color.md_yellow_900
                        )
                    )
                } else if (status == "Delayed") {
                    statusTextView.text = "DELAYED"
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
                } else if (status == "Int.") {
                    statusTextView.text = "INTERRUPTED"
                    statusTextView.setBackgroundColor(
                        ContextCompat.getColor(
                            MyApplication.instance, R.color.md_red_200
                        )
                    )
                } else if (status == "Cancl.") {
                    statusTextView.text = "CANCELLED"
                    statusTextView.setBackgroundColor(
                        ContextCompat.getColor(
                            MyApplication.instance, R.color.md_red_200
                        )
                    )
                } else if (liveStatus.contains(status)) {
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

    @SuppressLint("SetTextI18n")
    fun setVenue(
        status: String?,
        note: String?,
        venue: VenueIncludeCountry?,
        noteOrVenue: TextView
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
                } else {
                    noteOrVenue.text = "${venue.name} • ${venue.city}"
                }
            }
        }
    }

    fun setRunsWithTeamName(
        runs: List<RunWithTeam>?,
        localTeam: Team?,
        visitorTeam: Team?,
        localTeamName: TextView,
        localTeamImage: ImageView,
        localTeamRun: TextView,
        localTeamOver: TextView,
        visitorTeamName: TextView,
        visitorTeamImage: ImageView,
        visitorTeamRun: TextView,
        visitorTeamOver: TextView
    ) {
        if (runs?.size != 0 && runs != null) {
            localTeamName.text = runs[0].team?.name
            localTeamRun.text = runs[0].score.toString()
            localTeamOver.text = runs[0].overs.toString()

            visitorTeamName.text = runs[1].team?.name
            visitorTeamRun.text = runs[1].score.toString()
            visitorTeamOver.text = runs[1].overs.toString()

            Utils().also { it2 ->
                it2.loadImageWithPicasso(runs[0].team?.image_path, localTeamImage)
                it2.loadImageWithPicasso(runs[1].team?.image_path, visitorTeamImage)
            }

        } else {
            localTeamName.text = localTeam?.name
            visitorTeamName.text = visitorTeam?.name
            Utils().also { it2 ->
                it2.loadImageWithPicasso(localTeam?.image_path, localTeamImage)
                it2.loadImageWithPicasso(visitorTeam?.image_path, visitorTeamImage)
            }
        }
    }

    fun loadImageWithPicasso(imagePath: String?, imageView: ImageView) {
        if (!TextUtils.isEmpty(imagePath)) {
            Picasso.get().load(imagePath).fit()
                .placeholder(R.drawable.progress_animation).into(imageView)
        } else {
            imageView.setImageResource(R.drawable.ic_image)
        }
    }

}