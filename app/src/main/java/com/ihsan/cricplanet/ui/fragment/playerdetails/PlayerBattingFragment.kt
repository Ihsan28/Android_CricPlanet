package com.ihsan.cricplanet.ui.fragment.playerdetails

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.grid.PlayerDetailsGridAdapter
import com.ihsan.cricplanet.databinding.FragmentPlayerBattingBinding
import com.ihsan.cricplanet.model.PlayerGridItem
import com.ihsan.cricplanet.model.player.PlayerDetails
import com.ihsan.cricplanet.model.player.careerstats.Batting
import com.ihsan.cricplanet.utils.Utils

private const val TAG = "PlayerBattingFragment"

class PlayerBattingFragment : Fragment() {
    private lateinit var binding: FragmentPlayerBattingBinding
    private var keyValueList = mutableListOf<PlayerGridItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPlayerBattingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val player: PlayerDetails?
        val battingScoreBoredListView = binding.playerBattingListView
        val rowIndexColumnHeader = binding.rowIndexColumnHeader

        arguments.let {
            if (it != null) {
                player = it.getParcelable("player")
                Log.d("cricPlayerInfo", "onViewCreated: ${player?.id}")
                makeBattingCareer(player, rowIndexColumnHeader)
            }

            //Grid Adapter call
            battingScoreBoredListView.adapter =
                PlayerDetailsGridAdapter(requireContext(), keyValueList)
            Utils().setListViewHeightBasedOnItemsWithAdditionalHeight(battingScoreBoredListView)
        }
    }

    private fun makeBattingCareer(player: PlayerDetails?, rowIndexColumnHeader: LinearLayout) {
        val test = mutableListOf<Batting>()
        val test4day = mutableListOf<Batting>()
        val t20 = mutableListOf<Batting>()
        val t10 = mutableListOf<Batting>()
        val t20I = mutableListOf<Batting>()
        val odi = mutableListOf<Batting>()
        val league = mutableListOf<Batting>()
        val listA = mutableListOf<Batting>()


        for (career in player?.career!!) {
            career.batting.let { batting ->
                if (batting != null) {
                    Log.d(TAG, "onViewCreated: career type ${career.type}")
                    when (career.type) {
                        "Test/5day" -> {
                            test.add(batting)
                        }

                        "4day" -> {
                            test4day.add(batting)
                        }

                        "T20" -> {
                            t20.add(batting)
                        }

                        "T10" -> {
                            t10.add(batting)
                        }

                        "T20I" -> {
                            t20I.add(batting)
                        }

                        "ODI" -> {
                            odi.add(batting)
                        }

                        "League" -> {
                            league.add(batting)
                        }

                        "List A" -> {
                            listA.add(batting)
                        }
                    }
                }
            }
        }
        val scoreCardList = mutableListOf<Batting>()
        val columnNames = mutableListOf<String>()
        if (test.isNotEmpty()) {
            columnNames.add("Test")
            scoreCardList.add(careerType(test))
        }
        if (odi.isNotEmpty()) {
            columnNames.add("ODI")
            scoreCardList.add(careerType(odi))
        }
        if (t20.isNotEmpty()) {
            columnNames.add("T20")
            scoreCardList.add(careerType(t20))
        }
        if (t20I.isNotEmpty()) {
            columnNames.add("T20I")
            scoreCardList.add(careerType(t20I))
        }
        if (test4day.isNotEmpty()) {
            columnNames.add("4day")
            scoreCardList.add(careerType(test4day))
        }
        if (t10.isNotEmpty()) {
            columnNames.add("T10")
            scoreCardList.add(careerType(t10))
        }
        if (league.isNotEmpty()) {
            columnNames.add("League")
            scoreCardList.add(careerType(league))
        }
        if (listA.isNotEmpty()) {
            columnNames.add("List A")
            scoreCardList.add(careerType(listA))
        }

        // Add children views dynamically based on the number of items
        for (i in 0 until columnNames.size) {
            // Create a new TextView instance
            val textView = TextView(context).apply {
                id = View.generateViewId()
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.CENTER
                    weight = 3f
                }
                text = columnNames[i]
                textSize = 12f
                setTextColor(ContextCompat.getColor(context, R.color.md_blue_50))
                setTypeface(null, Typeface.BOLD)
            }
            Log.d(TAG, "onViewCreated: textView ${textView.text}")

            // Add the TextView to the parent layout
            rowIndexColumnHeader.addView(textView)
        }

        keyValueList.add(
            PlayerGridItem("Matches", scoreCardList.map { batting ->
                batting.matches.toString()
            })
        )
        keyValueList.add(PlayerGridItem("Innings", scoreCardList.map { batting ->
            batting.innings.toString()
        }))
        keyValueList.add(PlayerGridItem("Runs", scoreCardList.map { batting ->
            batting.runs_scored.toString()
        }))
        keyValueList.add(PlayerGridItem("Not Out", scoreCardList.map { batting ->
            batting.not_outs.toString()
        }))
        keyValueList.add(PlayerGridItem("Highest", scoreCardList.map { batting ->
            batting.highest_inning_score.toString()
        }))
        keyValueList.add(PlayerGridItem("SR", scoreCardList.map { batting ->
            batting.strike_rate.toString()
        }))
        keyValueList.add(PlayerGridItem("Balls", scoreCardList.map { batting ->
            batting.balls_faced.toString()
        }))
        keyValueList.add(PlayerGridItem("Average", scoreCardList.map { batting ->
            batting.average.toString()
        }))
        keyValueList.add(PlayerGridItem("Fours", scoreCardList.map { batting ->
            batting.four_x.toString()
        }))
        keyValueList.add(PlayerGridItem("Sixes", scoreCardList.map { batting ->
            batting.six_x.toString()
        }))
        keyValueList.add(PlayerGridItem("50s", scoreCardList.map { batting ->
            batting.fifties.toString()
        }))
        keyValueList.add(PlayerGridItem("100s", scoreCardList.map { batting ->
            batting.hundreds.toString()
        }))
    }

    private fun careerType(matches: MutableList<Batting>): Batting {
        val matchesCareer = Batting()
        matches.map { batting ->
            matchesCareer.matches = (matchesCareer.matches ?: 0) + (batting.matches ?: 0)
            matchesCareer.runs_scored =
                (matchesCareer.runs_scored ?: 0) + (batting.runs_scored ?: 0)
            matchesCareer.innings = (matchesCareer.innings ?: 0) + (batting.innings ?: 0)
            matchesCareer.not_outs = (matchesCareer.not_outs ?: 0) + (batting.not_outs ?: 0)
            matchesCareer.highest_inning_score = getHighestScore(matches)
            matchesCareer.strike_rate = getStrikeRate(matches)

            matchesCareer.average = getAverageRate(matches)
            matchesCareer.balls_faced =
                (matchesCareer.balls_faced ?: 0) + (batting.balls_faced ?: 0)
            matchesCareer.four_x = (matchesCareer.four_x ?: 0) + (batting.four_x ?: 0)
            matchesCareer.six_x = (matchesCareer.six_x ?: 0) + (batting.six_x ?: 0)
            matchesCareer.fifties = (matchesCareer.fifties ?: 0) + (batting.fifties ?: 0)
            matchesCareer.hundreds = (matchesCareer.hundreds ?: 0) + (batting.hundreds ?: 0)

        }
        return matchesCareer
    }

    private fun getHighestScore(battings: List<Batting>): Int {
        var highestScore = 0
        val str = "highest_inning_score"
        battings.map { batting ->
            batting.let {
                if (it.highest_inning_score != null) {
                    if (it.highest_inning_score!! > highestScore) {
                        highestScore = it.highest_inning_score!!
                    }
                }
            }
        }
        return highestScore
    }

    private fun getStrikeRate(battings: List<Batting>): Double {
        var strikeRate = 0.0
        battings.map { batting ->
            batting.let {
                if (it.strike_rate != null) {
                    strikeRate += it.strike_rate!!
                }
            }
        }

        return Utils().oneDecimalPoint(strikeRate / battings.size)
    }

    private fun getAverageRate(battings: List<Batting>): Double {
        var averageRate = 0.0
        battings.map { batting ->
            batting.let {
                if (it.average != null) {
                    averageRate += it.average!!
                }
            }
        }
        return Utils().oneDecimalPoint(averageRate / battings.size)
    }
}