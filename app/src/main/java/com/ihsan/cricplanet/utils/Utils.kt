package com.ihsan.cricplanet.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.GridView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.devhoony.lottieproegressdialog.LottieProgressDialog
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.Team
import com.ihsan.cricplanet.model.VenueIncludeCountry
import com.ihsan.cricplanet.model.fixture.FixtureByIdWithDetails
import com.ihsan.cricplanet.model.fixture.scoreboard.run.RunWithTeam
import com.squareup.picasso.Picasso
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

private const val TAG = "cricUtils"

class Utils {

    fun refreshMessage() {
        Toast.makeText(MyApplication.instance, "Refreshing", Toast.LENGTH_SHORT).show()
    }

    fun progressAnimationStart(context: Context, title: String): LottieProgressDialog {
        val progressbar = LottieProgressDialog(
            context,
            false,
            null,
            null,
            null,
            null,
            LottieProgressDialog.SAMPLE_1,
            title,
            null
        )
        progressbar.show()
        return progressbar
    }

    fun progressAnimationStop(progressbar: LottieProgressDialog) {
        progressbar.dismiss()
    }

    fun addBundle(
        fragment: Fragment,
        key: String,
        value: FixtureByIdWithDetails
    ): Fragment {
        val bundle = Bundle()
        bundle.putParcelable(key, value)
        fragment.arguments = bundle
        return fragment
    }

    fun oneDecimalPoint(number: Double?): Double {
        if (number == 0.00 || number == null) {
            return 0.0
        }
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.HALF_DOWN
        return df.format(number).toDouble()
        //return String.format("%.1f", number)
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
        Log.d(TAG, "upcomingYearDuration: $upcomingYearString")

        return upcomingYearString
    }

    fun recentMonthDuration(): String {

        // Get the current date and time
        val currentDateTime = LocalDateTime.now()

        // Add 2 months to the current date and time
        val lastMonthsDateTime = currentDateTime.minusMonths(2)

        // Format the new date and time as a string
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val lastMinuteDateTimeString = currentDateTime.format(formatter)
        val lastMonthsDateTimeString = lastMonthsDateTime.format(formatter)

        //Combine them in one string using comma as separator
        val upcomingYearString = "$lastMonthsDateTimeString,$lastMinuteDateTimeString"
        Log.d(TAG, "lastMonthDuration: $upcomingYearString")

        return upcomingYearString
    }

    fun getCurrentDate(): String {
        // Get the current date and time
        val currentDateTime = LocalDateTime.now()
        val tomorrowDateTime = currentDateTime.plusDays(1)
        // Format the new date as a string
        val formatter = DateTimeFormatter.ofPattern("yy-MM-dd")
        Log.d(
            TAG,
            "currentDateTime: ${currentDateTime.format(formatter)},${
                tomorrowDateTime.format(formatter)
            }"
        )
        return "${currentDateTime.format(formatter)},${tomorrowDateTime.format(formatter)}"
    }

    fun dateFormat(dateString: String?): List<String> {
        if (dateString == null) {
            return listOf("N/A", "N/A")
        }
        try {
            val apiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'000Z'")
            val targetFormat = DateTimeFormatter.ofPattern("dd-MM-yy/hh:mm a")
            val date = apiFormat.parse(dateString)
            return targetFormat.format(date).split("/")
        } catch (e: DateTimeParseException) {
            Log.d(TAG, "dateFormat: $e")
            return listOf("", "")
        }
    }
    fun getPlayerBorn(dateString: String): String {
        return try {
            val apiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val targetFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
            val date = apiFormat.parse(dateString)
            "${targetFormat.format(date)} (${getPlayerAge(dateString)})"
        } catch (e: DateTimeParseException) {
            Log.d(TAG, "dateFormat: $e")
            ""
        }
    }

    fun getPlayerAge(date: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate = dateFormat.parse(date)
        val currentTime = Calendar.getInstance().timeInMillis

        val days = (currentTime - (formattedDate?.time
            ?: 0)) / (60 * 60 * 24 * 1000) //Converting from mmSecond to days

        return if (days >= 365) {
            "${(days / 365).toInt()}y"
        } else {
            ""
        }
    }

    @SuppressLint("SetTextI18n")
    fun setRun(
        runs: List<RunWithTeam>?,
        localTeam: Team,
        localTeamRun: TextView,
        localTeamOver: TextView,
        visitorTeamRun: TextView,
        visitorTeamOver: TextView
    ) {
        if (runs != null && runs.size == 2) {
            if (localTeam.id == runs[0].team_id) {
                localTeamRun.text = "${runs[0].score}-${runs[0].wickets}"
                localTeamOver.text = "${runs[0].overs} ov"

                visitorTeamRun.text = "${runs[1].score}-${runs[1].wickets}"
                visitorTeamOver.text = "${runs[1].overs} ov"

            } else {
                localTeamRun.text = "${runs[1].score}-${runs[1].wickets}"
                localTeamOver.text = "${runs[1].overs} ov"

                visitorTeamRun.text = "${runs[0].score}-${runs[0].wickets}"
                visitorTeamOver.text = "${runs[0].overs} ov"
            }
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
                } else if (status?.let { isLive(it) } == true) {
                    statusTextView.text = "• LIVE"
                    statusTextView.setBackgroundColor(
                        ContextCompat.getColor(
                            MyApplication.instance, R.color.md_red_400
                        )
                    )
                } else {
                    statusTextView.text = status
                    statusTextView.setBackgroundColor(
                        ContextCompat.getColor(
                            MyApplication.instance, R.color.md_green_400
                        )
                    )
                }
            }
        }
    }

    fun isLive(status: String): Boolean {
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
        if (liveStatus.contains(status)) {
            return true
        }
        return false
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
                        "${venue.name} • ${venue.city} • ${venue.country.name}"
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

    fun setListViewHeightBasedOnItemsWithAdditionalHeight(listView: ListView) {
        val listAdapter = listView.adapter ?: return

        var totalHeight = 0
        for (i in 0 until listAdapter.count) {
            val listItem = listAdapter.getView(i, null, listView)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }

        val params = listView.layoutParams
        params.height = totalHeight + (listView.dividerHeight * (listAdapter.count - 1))+200
        listView.layoutParams = params
        listView.requestLayout()
    }

    fun setGridViewHeightBasedOnItemsWithAdditionalHeight(gridView: GridView) {
        val gridAdapter = gridView.adapter ?: return

        var totalHeight = 0
        val itemsPerRow = 3
        val numRows = (gridAdapter.count + itemsPerRow - 1) / itemsPerRow

        Log.d(TAG, "setGridViewHeightBasedOnItemsWithAdditionalHeight: col: ${gridView.numColumns} row: $numRows")

        for (i in 0 until numRows) {
            val listItem = gridAdapter.getView(i, null, gridView)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }

        val params = gridView.layoutParams
        params.height = totalHeight + (gridView.verticalSpacing * (numRows - 1)) + 200
        gridView.layoutParams = params
        gridView.requestLayout()
    }


    fun createCurvedTextView(context: Context,title: String): TextView {
        val textView = TextView(context)
        textView.id = View.generateViewId()
        textView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.CENTER
            weight = 3f
        }
        textView.text = title
        textView.textSize = 22f
        textView.setTextColor(ContextCompat.getColor(context, R.color.md_blue_50))
        textView.typeface = null
        textView.setTypeface(null, Typeface.BOLD)

        // Create a shape drawable with curved background
        val cornerRadius = 5f // Adjust the corner radius as desired
        val backgroundDrawable = ShapeDrawable().apply {
            shape = RoundRectShape(
                floatArrayOf(
                    cornerRadius, cornerRadius, cornerRadius, cornerRadius,
                    cornerRadius, cornerRadius, cornerRadius, cornerRadius
                ),
                null,
                null
            )
            paint.color=Color.DKGRAY // Set the desired background color
        }
        textView.background = backgroundDrawable

        return textView
    }

}