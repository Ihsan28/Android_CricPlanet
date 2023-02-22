package com.ihsan.cricplanet.ui.fragment.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihsan.cricplanet.adapter.TeamRankingAdapter
import com.ihsan.cricplanet.databinding.FragmentTestRankingBinding
import com.ihsan.cricplanet.model.team.TeamIncludeRanking

class TestRankingFragment : Fragment() {
    private val args: TestRankingFragmentArgs by navArgs()
    private lateinit var binding: FragmentTestRankingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTestRankingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var rankingListMen: List<TeamIncludeRanking>?

        val recyclerViewBatting = binding.recyclerviewTeamRanking
        recyclerViewBatting.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerViewBatting.setHasFixedSize(true)

        args?.let {
            //get match
            rankingListMen = it.rankingList?.ranking?.get(0)?.team
            recyclerViewBatting.adapter = rankingListMen?.let { it1 -> TeamRankingAdapter(it1) }
        }

    }
}