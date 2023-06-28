package com.ihsan.cricplanet.ui.fragment.playerdetails

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.grid.PlayerDetailsGridAdapter
import com.ihsan.cricplanet.databinding.FragmentPlayerBattingBowlingBinding
import com.ihsan.cricplanet.model.PlayerGridItem
import com.ihsan.cricplanet.model.player.PlayerDetails
import com.ihsan.cricplanet.model.player.careerstats.Batting
import com.ihsan.cricplanet.model.player.careerstats.Bowling
import com.ihsan.cricplanet.utils.Utils

private const val TAG = "PlayerBattingBowlingFragment"
class PlayerBattingBowlingFragment(private val isBatting:Boolean) : Fragment() {
    private lateinit var binding: FragmentPlayerBattingBowlingBinding
    private var keyValueList = mutableListOf<PlayerGridItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerBattingBowlingBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val player: PlayerDetails?
        val listView = binding.playerListView
        val rowIndexColumnHeader = binding.rowIndexColumnHeader

        arguments.let {
            if (it != null) {
                player = it.getParcelable("player")
                Log.d("cricPlayerInfo", "onViewCreated: ${player?.id}")
                if (isBatting) {
                    makeBowlingCareer(player, rowIndexColumnHeader)
                } else {
                    makeBattingCareer(player, rowIndexColumnHeader)
                }

                //List Adapter call
                listView.adapter =
                    PlayerDetailsGridAdapter(requireContext(), keyValueList)
                Utils().setListViewHeightBasedOnItemsWithAdditionalHeight(listView)
            }
        }
    }

    private fun makeBattingCareer(player: PlayerDetails?, rowIndexColumnHeader: LinearLayout) {
        val careers = player?.career

        val test = mutableListOf<Batting>()
        val test4day = mutableListOf<Batting>()
        val t20 = mutableListOf<Batting>()
        val t10 = mutableListOf<Batting>()
        val t20I = mutableListOf<Batting>()
        val odi = mutableListOf<Batting>()
        val league = mutableListOf<Batting>()
        val listA = mutableListOf<Batting>()

        for (career in careers!!) {
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

        //show all bowling career (subscription required)
        val careersWithSeasonAndLeague = mutableListOf<Pair<String, Batting>>()

        //make list of career with season and league
        careers.map { career ->
            career.batting.let { batting ->
                if (batting != null) {
                    val dateTimeList = Utils().dateFormat(career.updated_at)
                    careersWithSeasonAndLeague.add(
                        Pair(
                            "${career.type} | ${dateTimeList[0]} | ${dateTimeList[1]}",
                            career.batting as Batting
                        )
                    )
                }
            }
        }

        //make table for each career type
        careersWithSeasonAndLeague.map { mapOfBatting ->
            makeBattingSeasonScoreTable(mapOfBatting.first, mapOfBatting.second)
        }

        val scoreCardList = mutableListOf<Batting>()
        val columnNames = mutableListOf<String>()
        if (test.isNotEmpty()) {
            columnNames.add("Test")
            scoreCardList.add(careerTypeBatting(test))
        }
        if (odi.isNotEmpty()) {
            columnNames.add("ODI")
            scoreCardList.add(careerTypeBatting(odi))
        }
        if (t20.isNotEmpty()) {
            columnNames.add("T20")
            scoreCardList.add(careerTypeBatting(t20))
        }
        if (t20I.isNotEmpty()) {
            columnNames.add("T20I")
            scoreCardList.add(careerTypeBatting(t20I))
        }
        if (test4day.isNotEmpty()) {
            columnNames.add("4day")
            scoreCardList.add(careerTypeBatting(test4day))
        }
        if (t10.isNotEmpty()) {
            columnNames.add("T10")
            scoreCardList.add(careerTypeBatting(t10))
        }
        if (league.isNotEmpty()) {
            columnNames.add("League")
            scoreCardList.add(careerTypeBatting(league))
        }
        if (listA.isNotEmpty()) {
            columnNames.add("List A")
            scoreCardList.add(careerTypeBatting(listA))
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

    private fun makeBattingSeasonScoreTable(nameOfSeason: String, batting: Batting) {
        val keyValueList = mutableListOf<PlayerGridItem>()
        val keyValueList2 = mutableListOf<Pair<String, List<String>>>()
        keyValueList.add(PlayerGridItem("Matches", listOf(batting.matches.toString())))
        keyValueList.add(PlayerGridItem("Innings", listOf(batting.innings.toString())))
        keyValueList.add(PlayerGridItem("Runs", listOf(batting.runs_scored.toString())))
        keyValueList.add(PlayerGridItem("Not Out", listOf(batting.not_outs.toString())))
        keyValueList.add(PlayerGridItem("Highest", listOf(batting.highest_inning_score.toString())))
        keyValueList.add(PlayerGridItem("SR", listOf(batting.strike_rate.toString())))
        keyValueList.add(PlayerGridItem("Balls", listOf(batting.balls_faced.toString())))
        keyValueList.add(PlayerGridItem("Average", listOf(batting.average.toString())))
        keyValueList.add(PlayerGridItem("Fours", listOf(batting.four_x.toString())))
        keyValueList.add(PlayerGridItem("Sixes", listOf(batting.six_x.toString())))
        keyValueList.add(PlayerGridItem("50s", listOf(batting.fifties.toString())))
        keyValueList.add(PlayerGridItem("100s", listOf(batting.hundreds.toString())))

        val titleTextView = Utils().createCurvedTextView(requireContext(), nameOfSeason)
        val listView = GridView(context).apply {
            id = View.generateViewId()
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER
                weight = 3f
                numColumns = 3
            }
            adapter = PlayerDetailsGridAdapter(context, keyValueList)
        }

        Utils().setGridViewHeightBasedOnItemsWithAdditionalHeight(listView)
        binding.tableContainer.addView(titleTextView)
        binding.tableContainer.addView(listView)
    }

    private fun careerTypeBatting(matches: MutableList<Batting>): Batting {
        val matchesCareer = Batting()
        matches.map { batting ->
            matchesCareer.matches = (matchesCareer.matches ?: 0) + (batting.matches ?: 0)
            matchesCareer.runs_scored =
                (matchesCareer.runs_scored ?: 0) + (batting.runs_scored ?: 0)
            matchesCareer.innings = (matchesCareer.innings ?: 0) + (batting.innings ?: 0)
            matchesCareer.not_outs = (matchesCareer.not_outs ?: 0) + (batting.not_outs ?: 0)
            matchesCareer.highest_inning_score = getHighestScore(matches)
            matchesCareer.strike_rate = getStrikeRate(matches)

            matchesCareer.average = getAverageRateBatting(matches)
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

    private fun getAverageRateBatting(battings: List<Batting>): Double {
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

    private fun makeBowlingCareer(
        player: PlayerDetails?,
        rowIndexColumnHeader: LinearLayout
    ) {
        val careers = player?.career

        val test = mutableListOf<Bowling>()
        val test4day = mutableListOf<Bowling>()
        val t20 = mutableListOf<Bowling>()
        val t10 = mutableListOf<Bowling>()
        val t20I = mutableListOf<Bowling>()
        val odi = mutableListOf<Bowling>()
        val league = mutableListOf<Bowling>()
        val listA = mutableListOf<Bowling>()

        for (career in careers!!) {
            career.bowling.let { bowling ->
                if (bowling != null) {
                    Log.d(TAG, "onViewCreated: career type ${career.type}")
                    when (career.type) {
                        "Test/5day" -> {
                            test.add(bowling)
                        }

                        "4day" -> {
                            test4day.add(bowling)
                        }

                        "T20" -> {
                            t20.add(bowling)
                        }

                        "T10" -> {
                            t10.add(bowling)
                        }

                        "T20I" -> {
                            t20I.add(bowling)
                        }

                        "ODI" -> {
                            odi.add(bowling)
                        }

                        "League" -> {
                            league.add(bowling)
                        }

                        "List A" -> {
                            listA.add(bowling)
                        }
                    }
                }
            }
        }

        //show all bowling career (subscription required)
        val careersWithSeasonAndLeague = mutableListOf<Pair<String, Bowling>>()

        //make list of pair of career type and bowling
        careers.map { career ->
            career.bowling.let { bowling ->
                if (bowling != null) {
                    val dateTimeList = Utils().dateFormat(career.updated_at)
                    careersWithSeasonAndLeague.add(
                        Pair(
                            "${career.type} | ${dateTimeList[0]} | ${dateTimeList[1]}",
                            career.bowling as Bowling
                        )
                    )
                }
            }
        }

        //make table for each career type
        careersWithSeasonAndLeague.map { mapOfBowling ->
            makeBowlingSeasonScoreTable(mapOfBowling.first, mapOfBowling.second)
        }

        val scoreCardList = mutableListOf<Bowling>()
        val columnNames = mutableListOf<String>()

        if (test.isNotEmpty()) {
            columnNames.add("Test")
            scoreCardList.add(careerTypeBowling(test))
        }
        if (test4day.isNotEmpty()) {
            columnNames.add("4day")
            scoreCardList.add(careerTypeBowling(test4day))
        }
        if (t20.isNotEmpty()) {
            columnNames.add("T20")
            scoreCardList.add(careerTypeBowling(t20))
        }
        if (t10.isNotEmpty()) {
            columnNames.add("T10")
            scoreCardList.add(careerTypeBowling(t10))
        }
        if (t20I.isNotEmpty()) {
            columnNames.add("T20I")
            scoreCardList.add(careerTypeBowling(t20I))
        }
        if (odi.isNotEmpty()) {
            columnNames.add("ODI")
            scoreCardList.add(careerTypeBowling(odi))
        }
        if (league.isNotEmpty()) {
            columnNames.add("League")
            scoreCardList.add(careerTypeBowling(league))
        }
        if (listA.isNotEmpty()) {
            columnNames.add("List A")
            scoreCardList.add(careerTypeBowling(listA))
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

            // Add the TextView to the parent layout
            rowIndexColumnHeader.addView(textView)
        }

        keyValueList.add(PlayerGridItem("Matches", scoreCardList.map { bowling ->
            bowling.matches.toString()
        }))
        keyValueList.add(PlayerGridItem("Innings", scoreCardList.map { bowling ->
            bowling.innings.toString()
        }))
        keyValueList.add(PlayerGridItem("Runs", scoreCardList.map { bowling ->
            bowling.runs.toString()
        }))
        keyValueList.add(PlayerGridItem("Overs", scoreCardList.map { bowling ->
            bowling.overs.toString()
        }))
        keyValueList.add(PlayerGridItem("Eco", scoreCardList.map { bowling ->
            bowling.econ_rate.toString()
        }))
        keyValueList.add(PlayerGridItem("Wickets", scoreCardList.map { bowling ->
            bowling.wickets.toString()
        }))
        keyValueList.add(PlayerGridItem("Wide", scoreCardList.map { bowling ->
            bowling.wide.toString()
        }))
        keyValueList.add(PlayerGridItem("No Ball", scoreCardList.map { bowling ->
            bowling.noball.toString()
        }))
        keyValueList.add(PlayerGridItem("4w", scoreCardList.map { bowling ->
            bowling.four_wickets.toString()
        }))
        keyValueList.add(PlayerGridItem("5w", scoreCardList.map { bowling ->
            bowling.five_wickets.toString()
        }))
        keyValueList.add(PlayerGridItem("10w", scoreCardList.map { bowling ->
            bowling.ten_wickets.toString()
        }))
    }

    private fun makeBowlingSeasonScoreTable(nameOfSeason: String, bowlings: Bowling) {
        val keyValueList = mutableListOf<PlayerGridItem>()
        keyValueList.add(PlayerGridItem("Matches", listOf(bowlings.matches.toString())))
        keyValueList.add(PlayerGridItem("Innings", listOf(bowlings.innings.toString())))
        keyValueList.add(PlayerGridItem("Runs", listOf(bowlings.runs.toString())))
        keyValueList.add(PlayerGridItem("Overs", listOf(bowlings.overs.toString())))
        keyValueList.add(PlayerGridItem("Eco", listOf(bowlings.econ_rate.toString())))
        keyValueList.add(PlayerGridItem("Wickets", listOf(bowlings.wickets.toString())))
        keyValueList.add(PlayerGridItem("Wide", listOf(bowlings.wide.toString())))
        keyValueList.add(PlayerGridItem("No Ball", listOf(bowlings.noball.toString())))
        keyValueList.add(PlayerGridItem("4w", listOf(bowlings.four_wickets.toString())))
        keyValueList.add(PlayerGridItem("5w", listOf(bowlings.five_wickets.toString())))
        keyValueList.add(PlayerGridItem("10w", listOf(bowlings.ten_wickets.toString())))

        val titleTextView = Utils().createCurvedTextView(requireContext(), nameOfSeason)
        val listView = GridView(context).apply {
            id = View.generateViewId()
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER
                weight = 3f
                numColumns = 3
            }
            adapter = PlayerDetailsGridAdapter(context, keyValueList)
        }

        Utils().setGridViewHeightBasedOnItemsWithAdditionalHeight(listView)
        binding.tableContainer.addView(titleTextView)
        binding.tableContainer.addView(listView)
    }

    private fun careerTypeBowling(matches: MutableList<Bowling>): Bowling {
        val matchesCareer = Bowling()
        matches.map { bowling ->
            matchesCareer.matches = (matchesCareer.matches ?: 0) + (bowling.matches ?: 0)
            matchesCareer.innings = (matchesCareer.innings ?: 0) + (bowling.innings ?: 0)
            matchesCareer.runs = (matchesCareer.runs ?: 0) + (bowling.runs ?: 0)
            matchesCareer.overs = (matchesCareer.overs ?: 0.0) + (bowling.overs ?: 0.0)
            matchesCareer.econ_rate = getEconRate(matches)
            matchesCareer.rate = getRate(matches)
            matchesCareer.medians = (matchesCareer.medians ?: 0) + (bowling.medians ?: 0)
            matchesCareer.average = getAverageRateBowling(matches)
            matchesCareer.wickets = (matchesCareer.wickets ?: 0) + (bowling.wickets ?: 0)
            matchesCareer.wide = (matchesCareer.wide ?: 0) + (bowling.wide ?: 0)
            matchesCareer.noball = (matchesCareer.noball ?: 0) + (bowling.noball ?: 0)
            matchesCareer.four_wickets =
                (matchesCareer.four_wickets ?: 0) + (bowling.four_wickets ?: 0)
            matchesCareer.five_wickets =
                (matchesCareer.five_wickets ?: 0) + (bowling.five_wickets ?: 0)
            matchesCareer.ten_wickets =
                (matchesCareer.ten_wickets ?: 0) + (bowling.ten_wickets ?: 0)
        }
        return matchesCareer
    }

    private fun getAverageRateBowling(bowlings: List<Bowling>): Double {
        var averageRate = 0.0
        bowlings.map { bowling ->
            bowling.let {
                if (it.average != null) {
                    averageRate += it.strike_rate!!
                }
            }
        }
        return Utils().oneDecimalPoint(averageRate / bowlings.size)
    }

    private fun getEconRate(bowlings: List<Bowling>): Double {
        var econRate = 0.0
        bowlings.map { bowling ->
            bowling.let {
                if (it.econ_rate != null) {
                    econRate += it.econ_rate!!
                }
            }
        }
        return Utils().oneDecimalPoint(econRate / bowlings.size)
    }

    private fun getRate(bowlings: List<Bowling>): Double {
        var rate = 0.0
        bowlings.map { bowling ->
            bowling.let {
                if (it.rate != null) {
                    rate += it.rate!!
                }
            }
        }
        return Utils().oneDecimalPoint(rate / bowlings.size)
    }
}