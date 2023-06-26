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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.grid.PlayerDetailsGridAdapter
import com.ihsan.cricplanet.databinding.FragmentPlayerBowlingBinding
import com.ihsan.cricplanet.model.PlayerGridItem
import com.ihsan.cricplanet.model.player.PlayerDetails
import com.ihsan.cricplanet.model.player.careerstats.Bowling
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel

private const val TAG = "PlayerBowlingFragment"
class PlayerBowlingFragment : Fragment() {
    private lateinit var binding: FragmentPlayerBowlingBinding
    private var keyValueList = mutableListOf<PlayerGridItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPlayerBowlingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val player: PlayerDetails?
        val listView = binding.playerBowlingListView
        val rowIndexColumnHeader = binding.rowIndexColumnHeader
        arguments.let {
            if (it != null) {
                player = it.getParcelable("player")
                Log.d("cricPlayerInfo", "onViewCreated: ${player?.id}")
                makeBowlingCareer(player, rowIndexColumnHeader)
            }

            //List Adapter call
            listView.adapter = PlayerDetailsGridAdapter(requireContext(), keyValueList)
            Utils().setListViewHeightBasedOnItemsWithAdditionalHeight(listView)
        }
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
            career.bowling.let {bowling ->
                if (bowling != null) {
                    val dateTimeList=Utils().dateFormat(career.updated_at)
                    careersWithSeasonAndLeague.add(Pair("${career.type} | ${dateTimeList[0]} | ${dateTimeList[1]}", career.bowling as Bowling))
                }
            }
        }

        //make table for each career type
        careersWithSeasonAndLeague.map { mapOfBowling ->
            makeSeasonScoreTable(mapOfBowling.first, mapOfBowling.second)
        }

        val scoreCardList = mutableListOf<Bowling>()
        val columnNames = mutableListOf<String>()

        if (test.isNotEmpty()) {
            columnNames.add("Test")
            scoreCardList.add(careerType(test))
        }
        if (test4day.isNotEmpty()) {
            columnNames.add("4day")
            scoreCardList.add(careerType(test4day))
        }
        if (t20.isNotEmpty()) {
            columnNames.add("T20")
            scoreCardList.add(careerType(t20))
        }
        if (t10.isNotEmpty()) {
            columnNames.add("T10")
            scoreCardList.add(careerType(t10))
        }
        if (t20I.isNotEmpty()) {
            columnNames.add("T20I")
            scoreCardList.add(careerType(t20I))
        }
        if (odi.isNotEmpty()) {
            columnNames.add("ODI")
            scoreCardList.add(careerType(odi))
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

    private fun makeSeasonScoreTable(nameOfSeason: String, bowlings: Bowling) {
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

    private fun careerType(matches: MutableList<Bowling>): Bowling {
        val matchesCareer = Bowling()
        matches.map { bowling ->
            matchesCareer.matches = (matchesCareer.matches ?: 0) + (bowling.matches ?: 0)
            matchesCareer.innings = (matchesCareer.innings ?: 0) + (bowling.innings ?: 0)
            matchesCareer.runs = (matchesCareer.runs ?: 0) + (bowling.runs ?: 0)
            matchesCareer.overs = (matchesCareer.overs ?: 0.0) + (bowling.overs ?: 0.0)
            matchesCareer.econ_rate = getEconRate(matches)
            matchesCareer.rate = getRate(matches)
            matchesCareer.medians = (matchesCareer.medians ?: 0) + (bowling.medians ?: 0)
            matchesCareer.average = getAverageRate(matches)
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

    private fun getAverageRate(bowlings: List<Bowling>): Double {
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