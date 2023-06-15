package com.ihsan.cricplanet.ui.fragment.playerdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ihsan.cricplanet.adapter.grid.PlayerDetailsGridAdapter
import com.ihsan.cricplanet.databinding.FragmentPlayerBattingBinding
import com.ihsan.cricplanet.model.PlayerGridItem
import com.ihsan.cricplanet.model.fixture.CareerType
import com.ihsan.cricplanet.model.player.PlayerDetails
import com.ihsan.cricplanet.model.player.careerstats.Batting
import com.ihsan.cricplanet.utils.Utils
import kotlin.coroutines.cancellation.CancellationException

class PlayerBattingFragment : Fragment() {
    private lateinit var binding: FragmentPlayerBattingBinding
    private var keyValueList = mutableListOf<PlayerGridItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayerBattingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val player: PlayerDetails?
        val listView = binding.playerBattingListView
        arguments.let {
            if (it != null) {
                player = it.getParcelable("player")
                Log.d("cricPlayerInfo", "onViewCreated: ${player?.id}")
                var battingTest= listOf<List<Batting>>()
                var battingT20=mutableListOf<Batting>()
                var battingOdi=mutableListOf<Batting>()
                var battingLeague=mutableListOf<Batting>()
                var countCareerType= CareerType(0,0,0,0,0)

                player?.career?.map { career ->
                    career.batting.let {batting->
                        if (batting != null) {
                            when (career.type) {
                                "Test/5day" -> {
                                    countCareerType.Test++
                                    /*battingTest = batting
                                    battingTest.matches = battingTest.matches!! + batting.matches!!
                                    battingTest.innings = battingTest.innings!! + batting.innings!!
                                    battingTest.runs_scored = battingTest.runs_scored!! + batting.runs_scored!!
                                    battingTest.not_outs = battingTest.not_outs!! + batting.not_outs!!*/

                                }
                                "T20" -> {
                                    countCareerType.T20++
                                    //battingT20 = batting
                                }
                                "T20I" -> {
                                    countCareerType.T20I++
                                    //battingT20 = batting
                                }
                                "ODI" -> {
                                    countCareerType.ODI++
                                    //battingOdi = batting
                                }
                                "league" -> {
                                    countCareerType.League++
                                    //battingLeague = batting
                                }
                            }
                        }

                    }
                }
                val test = player?.career?.get(0)?.batting
                val t20 = player?.career?.get(1)?.batting
                val odi = player?.career?.get(2)?.batting
                val league = player?.career?.get(3)?.batting
                val ut = Utils()

                keyValueList.add(
                    PlayerGridItem(
                        "Matches",
                        (test?.matches ?: 0).toString(),
                        (t20?.matches ?: 0).toString(),
                        (odi?.matches ?: 0).toString(),
                        (league?.matches ?: 0).toString()
                    )
                )
                keyValueList.add(
                    PlayerGridItem(
                        "Innings",
                        (test?.innings ?: 0).toString(),
                        (t20?.innings ?: 0).toString(),
                        (odi?.innings ?: 0).toString(),
                        (league?.innings ?: 0).toString()
                    )
                )
                keyValueList.add(
                    PlayerGridItem(
                        "Runs",
                        (test?.runs_scored ?: 0).toString(),
                        (t20?.runs_scored ?: 0).toString(),
                        (odi?.runs_scored ?: 0).toString(),
                        (league?.runs_scored ?: 0).toString()
                    )
                )
                keyValueList.add(
                    PlayerGridItem(
                        "Not Out",
                        (test?.not_outs ?: 0).toString(),
                        (t20?.not_outs ?: 0).toString(),
                        (odi?.not_outs ?: 0).toString(),
                        (league?.not_outs ?: 0).toString()
                    )
                )
                keyValueList.add(
                    PlayerGridItem(
                        "Highest",
                        (test?.highest_inning_score ?: 0).toString(),
                        (t20?.highest_inning_score ?: 0).toString(),
                        (odi?.highest_inning_score ?: 0).toString(),
                        (league?.highest_inning_score ?: 0).toString()
                    )
                )
                keyValueList.add(
                    PlayerGridItem(
                        "SR",
                        ut.twoDecimal(test?.strike_rate),
                        ut.twoDecimal(t20?.strike_rate),
                        ut.twoDecimal(odi?.strike_rate),
                        ut.twoDecimal(league?.strike_rate)
                    )
                )
                keyValueList.add(
                    PlayerGridItem(
                        "Balls",
                        (test?.balls_faced ?: 0).toString(),
                        (t20?.balls_faced ?: 0).toString(),
                        (odi?.balls_faced ?: 0).toString(),
                        (league?.balls_faced ?: 0).toString()
                    )
                )
                keyValueList.add(
                    PlayerGridItem(
                        "Average",
                        ut.twoDecimal(test?.strike_rate),
                        ut.twoDecimal(test?.strike_rate),
                        ut.twoDecimal(test?.strike_rate),
                        ut.twoDecimal(test?.strike_rate)
                    )
                )
                keyValueList.add(
                    PlayerGridItem(
                        "Fours",
                        (test?.four_x ?: 0).toString(),
                        (t20?.four_x ?: 0).toString(),
                        (odi?.four_x ?: 0).toString(),
                        (league?.four_x ?: 0).toString()
                    )
                )
                keyValueList.add(
                    PlayerGridItem(
                        "Sixes",
                        (test?.six_x ?: 0).toString(),
                        (t20?.six_x ?: 0).toString(),
                        (odi?.six_x ?: 0).toString(),
                        (league?.six_x ?: 0).toString()
                    )
                )
                keyValueList.add(
                    PlayerGridItem(
                        "50s",
                        (test?.fifties ?: 0).toString(),
                        (t20?.fifties ?: 0).toString(),
                        (odi?.fifties ?: 0).toString(),
                        (league?.fifties ?: 0).toString()
                    )
                )
                keyValueList.add(
                    PlayerGridItem(
                        "100s",
                        (test?.hundreds ?: 0).toString(),
                        (t20?.hundreds ?: 0).toString(),
                        (odi?.hundreds ?: 0).toString(),
                        (league?.hundreds ?: 0).toString()
                    )
                )
            }

            //Grid Adapter call
            listView.adapter = PlayerDetailsGridAdapter(requireContext(), keyValueList)
        }
    }
}