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
import com.ihsan.cricplanet.adapter.grid.PlayerDetailsAdapter
import com.ihsan.cricplanet.databinding.FragmentPlayerBattingBowlingBinding
import com.ihsan.cricplanet.model.player.PlayerDetails
import com.ihsan.cricplanet.model.player.careerstats.Batting
import com.ihsan.cricplanet.model.player.careerstats.Bowling
import com.ihsan.cricplanet.utils.Utils

private const val TAG = "PlayerBattingBowlingFragment"

class PlayerBattingBowlingFragment(private val isBatting: Boolean = true) : Fragment() {
    private lateinit var binding: FragmentPlayerBattingBowlingBinding
    private var keyValueList = mutableListOf<kotlin.Pair<String, List<String>>>()

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
                    makeBattingCareer(player, rowIndexColumnHeader)
                } else {
                    makeBowlingCareer(player, rowIndexColumnHeader)
                }

                //List Adapter call
                listView.adapter =
                    PlayerDetailsAdapter(requireContext(), keyValueList)
                Utils().setListViewHeightBasedOnItemsWithAdditionalHeight(listView)
            }
        }
    }

    private fun makeBattingCareer(player: PlayerDetails?, rowIndexColumnHeader: LinearLayout) {
        val careers = player?.career

        var matchCareers = HashMap<String, MutableList<Batting>>()

        for (career in careers!!) {
            career.batting.let { batting ->
                if (batting != null) {
                    Log.d(TAG, "onViewCreated: career type ${career.type}")
                    if (matchCareers.containsKey(career.type)) {
                        matchCareers[career.type]?.add(batting)
                    } else {
                        //add key and value on hashmap
                        matchCareers[career.type.toString()] = mutableListOf(batting)
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

        val calculatedScoreCardList = mutableListOf<Batting>()

        matchCareers.map { career ->
            // Create a new TextView instance
            val textView = TextView(context).apply {
                id = View.generateViewId()
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.CENTER
                    weight = 3f
                }
                text = career.key
                textSize = 12f
                setTextColor(ContextCompat.getColor(context, R.color.md_blue_50))
                setTypeface(null, Typeface.BOLD)
            }
            Log.d(TAG, "onViewCreated: textView ${textView.text}")

            // Add the TextView to the parent layout
            rowIndexColumnHeader.addView(textView)

            calculatedScoreCardList.add(calculatingCareerBatting(career.value))
        }

        keyValueList.add(
            Pair("Matches", calculatedScoreCardList.map { batting ->
                batting.matches.toString()
            })
        )
        keyValueList.add(Pair("Innings", calculatedScoreCardList.map { batting ->
            batting.innings.toString()
        }))
        keyValueList.add(Pair("Runs", calculatedScoreCardList.map { batting ->
            batting.runs_scored.toString()
        }))
        keyValueList.add(Pair("Not Out", calculatedScoreCardList.map { batting ->
            batting.not_outs.toString()
        }))
        keyValueList.add(Pair("Highest", calculatedScoreCardList.map { batting ->
            batting.highest_inning_score.toString()
        }))
        keyValueList.add(Pair("SR", calculatedScoreCardList.map { batting ->
            batting.strike_rate.toString()
        }))
        keyValueList.add(Pair("Balls", calculatedScoreCardList.map { batting ->
            batting.balls_faced.toString()
        }))
        keyValueList.add(Pair("Average", calculatedScoreCardList.map { batting ->
            batting.average.toString()
        }))
        keyValueList.add(Pair("Fours", calculatedScoreCardList.map { batting ->
            batting.four_x.toString()
        }))
        keyValueList.add(Pair("Sixes", calculatedScoreCardList.map { batting ->
            batting.six_x.toString()
        }))
        keyValueList.add(Pair("50s", calculatedScoreCardList.map { batting ->
            batting.fifties.toString()
        }))
        keyValueList.add(Pair("100s", calculatedScoreCardList.map { batting ->
            batting.hundreds.toString()
        }))
    }

    private fun makeBattingSeasonScoreTable(nameOfSeason: String, batting: Batting) {
        val keyValueList = mutableListOf<Pair<String, List<String>>>()

        keyValueList.add(Pair("Matches", listOf(batting.matches.toString())))
        keyValueList.add(Pair("Innings", listOf(batting.innings.toString())))
        keyValueList.add(Pair("Runs", listOf(batting.runs_scored.toString())))
        keyValueList.add(Pair("Not Out", listOf(batting.not_outs.toString())))
        keyValueList.add(Pair("Highest", listOf(batting.highest_inning_score.toString())))
        keyValueList.add(Pair("SR", listOf(batting.strike_rate.toString())))
        keyValueList.add(Pair("Balls", listOf(batting.balls_faced.toString())))
        keyValueList.add(Pair("Average", listOf(batting.average.toString())))
        keyValueList.add(Pair("Fours", listOf(batting.four_x.toString())))
        keyValueList.add(Pair("Sixes", listOf(batting.six_x.toString())))
        keyValueList.add(Pair("50s", listOf(batting.fifties.toString())))
        keyValueList.add(Pair("100s", listOf(batting.hundreds.toString())))

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
            adapter = PlayerDetailsAdapter(context, keyValueList)
        }

        Utils().setGridViewHeightBasedOnItemsWithAdditionalHeight(listView)
        binding.tableContainer.addView(titleTextView)
        binding.tableContainer.addView(listView)
    }

    private fun calculatingCareerBatting(matches: MutableList<Batting>): Batting {
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

        val matchCareers = HashMap<String, MutableList<Bowling>>()

        for (career in careers!!) {
            career.bowling.let { bowling ->
                if (bowling != null) {
                    Log.d(TAG, "onViewCreated: career type ${career.type}")
                    if (matchCareers.containsKey(career.type)) {
                        matchCareers[career.type]?.add(bowling)
                    } else {
                        matchCareers[career.type.toString()] = mutableListOf(bowling)
                    }
                }
            }
        }

        //show all bowling career (subscription required)
        val careersWithSeasonAndLeague = mutableListOf<kotlin.Pair<String, Bowling>>()

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
        matchCareers.map { career ->
            // Create a new TextView instance
            val textView = TextView(context).apply {
                id = View.generateViewId()
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.CENTER
                    weight = 3f
                }
                text = career.key
                textSize = 12f
                setTextColor(ContextCompat.getColor(context, R.color.md_blue_50))
                setTypeface(null, Typeface.BOLD)
            }

            // Add the TextView to the parent layout
            rowIndexColumnHeader.addView(textView)
            scoreCardList.add(calculatingCareerBowling(career.value))
        }

        keyValueList.add(Pair("Matches", scoreCardList.map { bowling ->
            bowling.matches.toString()
        }))
        keyValueList.add(Pair("Innings", scoreCardList.map { bowling ->
            bowling.innings.toString()
        }))
        keyValueList.add(Pair("Runs", scoreCardList.map { bowling ->
            bowling.runs.toString()
        }))
        keyValueList.add(Pair("Overs", scoreCardList.map { bowling ->
            bowling.overs.toString()
        }))
        keyValueList.add(Pair("Eco", scoreCardList.map { bowling ->
            bowling.econ_rate.toString()
        }))
        keyValueList.add(Pair("Wickets", scoreCardList.map { bowling ->
            bowling.wickets.toString()
        }))
        keyValueList.add(Pair("Wide", scoreCardList.map { bowling ->
            bowling.wide.toString()
        }))
        keyValueList.add(Pair("No Ball", scoreCardList.map { bowling ->
            bowling.noball.toString()
        }))
        keyValueList.add(Pair("4w", scoreCardList.map { bowling ->
            bowling.four_wickets.toString()
        }))
        keyValueList.add(Pair("5w", scoreCardList.map { bowling ->
            bowling.five_wickets.toString()
        }))
        keyValueList.add(Pair("10w", scoreCardList.map { bowling ->
            bowling.ten_wickets.toString()
        }))
    }

    private fun makeBowlingSeasonScoreTable(nameOfSeason: String, bowlings: Bowling) {
        val keyValueList = mutableListOf<Pair<String, List<String>>>()
        keyValueList.add(Pair("Matches", listOf(bowlings.matches.toString())))
        keyValueList.add(Pair("Innings", listOf(bowlings.innings.toString())))
        keyValueList.add(Pair("Runs", listOf(bowlings.runs.toString())))
        keyValueList.add(Pair("Overs", listOf(bowlings.overs.toString())))
        keyValueList.add(Pair("Eco", listOf(bowlings.econ_rate.toString())))
        keyValueList.add(Pair("Wickets", listOf(bowlings.wickets.toString())))
        keyValueList.add(Pair("Wide", listOf(bowlings.wide.toString())))
        keyValueList.add(Pair("No Ball", listOf(bowlings.noball.toString())))
        keyValueList.add(Pair("4w", listOf(bowlings.four_wickets.toString())))
        keyValueList.add(Pair("5w", listOf(bowlings.five_wickets.toString())))
        keyValueList.add(Pair("10w", listOf(bowlings.ten_wickets.toString())))

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
            adapter = PlayerDetailsAdapter(context, keyValueList)
        }

        Utils().setGridViewHeightBasedOnItemsWithAdditionalHeight(listView)
        binding.tableContainer.addView(titleTextView)
        binding.tableContainer.addView(listView)
    }

    private fun calculatingCareerBowling(matches: MutableList<Bowling>): Bowling {
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