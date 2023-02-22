package com.ihsan.cricplanet.ui.fragment.ranking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.TeamRankingAdapter
import com.ihsan.cricplanet.databinding.FragmentODIRankingBinding
import com.ihsan.cricplanet.databinding.FragmentTTwentyRankingBinding
import com.ihsan.cricplanet.model.team.TeamIncludeRanking

class TTwentyRankingFragment : Fragment() {
    private val args:TTwentyRankingFragmentArgs by navArgs()
    private lateinit var binding:FragmentTTwentyRankingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentTTwentyRankingBinding.inflate(inflater,container,false)
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