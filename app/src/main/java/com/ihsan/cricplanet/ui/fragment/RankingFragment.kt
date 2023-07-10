package com.ihsan.cricplanet.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihsan.cricplanet.adapter.TeamRankingAdapter
import com.ihsan.cricplanet.databinding.FragmentRankingBinding
import com.ihsan.cricplanet.model.team.TeamIncludeRanking
import com.ihsan.cricplanet.utils.BottomSpaceItemDecoration
import com.ihsan.cricplanet.utils.Utils

class RankingFragment : Fragment() {
    private val args: RankingFragmentArgs by navArgs()
    private lateinit var binding: FragmentRankingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRankingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = Utils().progressAnimationStart(requireContext(), "Loading Teams")
        var rankingList: List<TeamIncludeRanking>?

        //binding toggle button
        val menRankingButton = binding.menButton
        val womenRankingButton = binding.womenButton
        val menWomenToggleButton = binding.genderToggleGroup

        //Assigning Recycler view
        val recyclerviewTeamRanking = binding.recyclerviewTeamRanking
        recyclerviewTeamRanking.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerviewTeamRanking.setHasFixedSize(true)
        recyclerviewTeamRanking.addItemDecoration(BottomSpaceItemDecoration())

        //getting navigation argument
        args.let { rankingArgs ->
            //get match
            rankingList = getList(rankingArgs)
            recyclerviewTeamRanking.adapter = rankingList?.let { it1 -> TeamRankingAdapter(it1) }
            Utils().progressAnimationStop(progressBar)
            //toggle button click listener
            menWomenToggleButton.addOnButtonCheckedListener { _, checkedId, isChecked ->
                rankingList = when (isChecked) {
                    true -> when (checkedId) {
                        menRankingButton.id -> getListForMen(rankingArgs)
                        womenRankingButton.id -> getListForWomen(rankingArgs)
                        else -> getList(rankingArgs)
                    }

                    else -> getList(rankingArgs)
                }

                //showing for null
                if ((rankingList?.size ?: 0) == 0) {
                    Toast.makeText(requireActivity(), "Not Available", Toast.LENGTH_SHORT).show()
                }

                //assigning adapter
                recyclerviewTeamRanking.adapter = rankingList?.let { it1 -> TeamRankingAdapter(it1) }
            }
            Utils().progressAnimationStop(progressBar)
        }
    }

    private fun getListForMen(rankingArgs: RankingFragmentArgs): List<TeamIncludeRanking>? {
        return when (rankingArgs.category) {
            "T20" -> rankingArgs.rankingList?.ranking?.get(0)?.team
            "ODI" -> rankingArgs.rankingList?.ranking?.get(1)?.team
            "TEST" -> rankingArgs.rankingList?.ranking?.get(2)?.team
            else -> rankingArgs.rankingList?.ranking?.get(0)?.team
        }
    }

    private fun getListForWomen(rankingArgs: RankingFragmentArgs): List<TeamIncludeRanking>? {
        return when (rankingArgs.category) {
            "T20" -> rankingArgs.rankingList?.ranking?.get(3)?.team
            "ODI" -> rankingArgs.rankingList?.ranking?.get(4)?.team
            "TEST" -> rankingArgs.rankingList?.ranking?.get(5)?.team
            else -> rankingArgs.rankingList?.ranking?.get(3)?.team
        }
    }

    private fun getList(rankingArgs: RankingFragmentArgs): List<TeamIncludeRanking> {
        val menList = when (rankingArgs.category) {
            "T20" -> rankingArgs.rankingList?.ranking?.get(0)?.team
            "ODI" -> rankingArgs.rankingList?.ranking?.get(1)?.team
            "TEST" -> rankingArgs.rankingList?.ranking?.get(2)?.team
            else -> rankingArgs.rankingList?.ranking?.get(0)?.team
        }

        val womenList = when (rankingArgs.category) {
            "T20" -> rankingArgs.rankingList?.ranking?.get(3)?.team
            "ODI" -> rankingArgs.rankingList?.ranking?.get(4)?.team
            "TEST" -> rankingArgs.rankingList?.ranking?.get(5)?.team
            else -> rankingArgs.rankingList?.ranking?.get(3)?.team
        }

        val list = mutableListOf<TeamIncludeRanking>()
        if (menList != null) {
            list.addAll(menList)
        }

        if (womenList != null) {
            list.addAll(womenList)
        }
        return list.sortedWith(compareBy<TeamIncludeRanking> { it.position }.thenByDescending { it.ranking?.rating })
    }
}