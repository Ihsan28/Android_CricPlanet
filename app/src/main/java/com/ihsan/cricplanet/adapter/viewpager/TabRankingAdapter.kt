package com.ihsan.cricplanet.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihsan.cricplanet.model.Tab
import com.ihsan.cricplanet.model.team.GlobalTeamRanking
import com.ihsan.cricplanet.model.team.GlobalTeamRankingList
import com.ihsan.cricplanet.ui.fragment.RankingFragment
import com.ihsan.cricplanet.utils.Utils

class TabRankingAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle,
    private val ranking: List<GlobalTeamRanking>
) : FragmentStateAdapter(manager, lifecycle) {
    companion object {
        val rankingListTab = listOf(
            Tab(RankingFragment(), "T20"),
            Tab(RankingFragment(), "ODI"),
            Tab(RankingFragment(), "TEST")
        )
    }

    override fun getItemCount(): Int {
        return rankingListTab.size
    }

    override fun createFragment(position: Int): Fragment {
        return Utils().addBundle(
            rankingListTab[position].fragment,
            "category",
            rankingListTab[position].category,
            "rankingList",
            GlobalTeamRankingList(ranking.map { it })
        )
    }
}

