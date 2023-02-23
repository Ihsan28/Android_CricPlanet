package com.ihsan.cricplanet.ui.fragment.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihsan.cricplanet.adapter.TeamRankingAdapter
import com.ihsan.cricplanet.databinding.FragmentTTwentyRankingBinding
import com.ihsan.cricplanet.model.team.TeamIncludeRanking

class TTwentyRankingFragment : Fragment() {
    private val args: TTwentyRankingFragmentArgs by navArgs()
    private lateinit var binding: FragmentTTwentyRankingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTTwentyRankingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var rankingListMen: List<TeamIncludeRanking>?
        var rankingListWomen: List<TeamIncludeRanking>?

        val recyclerViewBatting = binding.recyclerviewTeamRanking
        recyclerViewBatting.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerViewBatting.setHasFixedSize(true)

        args?.let {
            //get match
            Toast.makeText(requireActivity(), it.category, Toast.LENGTH_SHORT).show()
            when (it.category) {
                "T20" -> rankingListMen = it.rankingList?.ranking?.get(0)?.team
                "ODI" -> rankingListMen = it.rankingList?.ranking?.get(1)?.team
                "TEST" -> rankingListMen = it.rankingList?.ranking?.get(2)?.team
                else -> rankingListMen = it.rankingList?.ranking?.get(0)?.team
            }
            recyclerViewBatting.adapter = rankingListMen?.let { it1 -> TeamRankingAdapter(it1) }
        }
    }
}