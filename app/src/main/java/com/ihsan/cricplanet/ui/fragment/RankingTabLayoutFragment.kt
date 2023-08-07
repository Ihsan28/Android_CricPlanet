package com.ihsan.cricplanet.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.google.android.material.tabs.TabLayoutMediator
import com.ihsan.cricplanet.adapter.viewpager.TabRankingAdapter
import com.ihsan.cricplanet.databinding.FragmentRankingTabLayoutBinding
import com.ihsan.cricplanet.utils.CheckNetwork.Companion.networkStatus
import com.ihsan.cricplanet.utils.MyApplication
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel
import kotlinx.coroutines.launch

class RankingTabLayoutFragment : Fragment() {
    private lateinit var binding: FragmentRankingTabLayoutBinding
    private val viewModel: CricViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        networkStatus.observe(viewLifecycleOwner) {
            if (it.connection) {
                refreshRanking()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentRankingTabLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Refresh Layout
        val refreshLayout = binding.swipeLayout

        //Tab layout
        val tabLayout = binding.tabLayoutRanking
        val viewPage = binding.viewPager2Ranking

        val progressBar= Utils().progressAnimationStart(requireContext(), "Loading Ranking...")

        viewModel.getTeamRanking()
        viewModel.teamRanking.observe(viewLifecycleOwner) {
            Log.d("cricRankingTab", "onViewCreated Parent RankingList: $it")
            val tabRankingAdapter = TabRankingAdapter(childFragmentManager, lifecycle, it)
            viewPage.adapter = tabRankingAdapter
            TabLayoutMediator(tabLayout, viewPage) { tab, position ->
                tab.text = TabRankingAdapter.rankingListTab[position].category
            }.attach()
            Utils().progressAnimationStop(progressBar)
        }

        //Refreshing The Home Page
        refreshLayout.setOnRefreshListener {
            refreshRanking()
            refreshLayout.isRefreshing = false
        }
    }
    private fun refreshRanking() {
        viewModel.viewModelScope.launch {
            //getting data for league from Api
            viewModel.getTeamRanking()
            Utils().refreshMessage()
        }
    }
}