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
        val listView = binding.playerBattingListView
        arguments.let {
            if (it != null) {
                player = it.getParcelable("player")
                Log.d("cricPlayerInfo", "onViewCreated: ${player?.id}")
                val countCareerType = mutableListOf<MutableList<Batting>>()
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
                Log.d(TAG, "onViewCreated: $countCareerType")/*val test = countCareerType.testMatches?.let { it1 -> careerType(it1) }
                val t20 = countCareerType.t20Matches?.let { it1 -> careerType(it1) }
                val odi = countCareerType.odiMatches?.let { it1 -> careerType(it1) }
                val league = countCareerType.leagueMatches?.let { it1 -> careerType(it1) }*//*val test = player?.career?.get(0)?.batting
                val t20 = player?.career?.get(1)?.batting
                val odi = player?.career?.get(2)?.batting
                val league = player?.career?.get(3)?.batting*/
                val scoreCardList = mutableListOf<Batting>()
                scoreCardList.add(careerType(test))
                scoreCardList.add(careerType(test4day))
                scoreCardList.add(careerType(t10))
                scoreCardList.add(careerType(t20))
                scoreCardList.add(careerType(t20I))
                scoreCardList.add(careerType(odi))
                scoreCardList.add(careerType(league))
                scoreCardList.add(careerType(listA))


                if (test.size > 0) {

                }

                keyValueList.add(
                    PlayerGridItem(
                        "Matches",
                            scoreCardList.map {
                                it.matches.toString()
                            }
                    )
                )
                keyValueList.add(
                    PlayerGridItem(
                        "Innings",
                        scoreCardList.map {
                                it.innings.toString()
                            }
                    )
                )
                keyValueList.add(
                    PlayerGridItem(
                        "Runs",
                        scoreCardList.map {
                                it.runs_scored.toString()
                            }
                    )
                )
            /*keyValueList.add(
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
                )*/
            }

            //Grid Adapter call
            listView.adapter = PlayerDetailsGridAdapter(requireContext(), keyValueList)
        }
    }

    fun stringValidation(item: Int?): String {
        return (item ?: 0).toString()
    }

    fun stringValidation(item: Double?): String {
        return (item ?: 0).toString()
    }

    private fun careerType(matches: MutableList<Batting>): Batting {
        val matchesCareer = Batting()
        matches.map { batting ->
            Utils().let { ut ->
                matchesCareer.matches = (matchesCareer.matches ?: 0) + (batting.matches ?: 0)
                matchesCareer.runs_scored =
                    (matchesCareer.runs_scored ?: 0) + (batting.runs_scored ?: 0)
                matchesCareer.innings = (matchesCareer.innings ?: 0) + (batting.innings ?: 0)
                matchesCareer.not_outs = (matchesCareer.not_outs ?: 0) + (batting.not_outs ?: 0)
                matchesCareer.highest_inning_score = ut.getHighestScore(matches)
                matchesCareer.strike_rate = ut.getStrikeRate(matches)
                matchesCareer.average = ut.getAverageRate(matches)
                matchesCareer.balls_faced =
                    (matchesCareer.balls_faced ?: 0) + (batting.balls_faced ?: 0)
                matchesCareer.four_x = (matchesCareer.four_x ?: 0) + (batting.four_x ?: 0)
                matchesCareer.six_x = (matchesCareer.six_x ?: 0) + (batting.six_x ?: 0)
                matchesCareer.fifties = (matchesCareer.fifties ?: 0) + (batting.fifties ?: 0)
                matchesCareer.hundreds = (matchesCareer.hundreds ?: 0) + (batting.hundreds ?: 0)
            }
        }
        return matchesCareer
    }
}