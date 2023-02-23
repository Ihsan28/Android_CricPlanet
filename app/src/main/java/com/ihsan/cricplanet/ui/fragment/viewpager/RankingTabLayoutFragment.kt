package com.ihsan.cricplanet.ui.fragment.viewpager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.ihsan.cricplanet.adapter.TeamRankingAdapter
import com.ihsan.cricplanet.adapter.viewpager.TabMatchAdapter
import com.ihsan.cricplanet.adapter.viewpager.TabRankingAdapter
import com.ihsan.cricplanet.databinding.FragmentRankingTabLayoutBinding
import com.ihsan.cricplanet.viewmodel.CricViewModel

class RankingTabLayoutFragment : Fragment() {
    private lateinit var binding:FragmentRankingTabLayoutBinding
    private val viewModel:CricViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentRankingTabLayoutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Tab layout
        val tabLayout = binding.tabLayoutRanking
        val viewPage = binding.viewPager2Ranking

        viewModel.getTeamRanking()
        viewModel.teamRanking.observe(viewLifecycleOwner) {
            Log.d("cricRankingTab", "onViewCreated Parent RankingList: $it")
            val tabRankingAdapter = TabRankingAdapter(childFragmentManager, lifecycle,it)
            viewPage.adapter = tabRankingAdapter
            TabLayoutMediator(tabLayout, viewPage) { tab, position ->
                tab.text = TabRankingAdapter.rankingListTab[position].category
            }.attach()
        }
    }
}