package com.ihsan.cricplanet.adapter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihsan.cricplanet.model.Tab
import com.ihsan.cricplanet.model.team.GlobalTeamRanking
import com.ihsan.cricplanet.model.team.GlobalTeamRankingList
import com.ihsan.cricplanet.ui.fragment.ranking.ODIRankingFragment
import com.ihsan.cricplanet.ui.fragment.ranking.TTwentyRankingFragment
import com.ihsan.cricplanet.ui.fragment.ranking.TestRankingFragment

class TabRankingAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle,
    private val ranking: List<GlobalTeamRanking>
) : FragmentStateAdapter(manager, lifecycle) {
    companion object {
        val rankingListTab = listOf(
            Tab(TTwentyRankingFragment(), "T20"),
            Tab(ODIRankingFragment(), "ODI"),
            Tab(TestRankingFragment(), "TEST")
        )
    }

    override fun getItemCount(): Int {
        return rankingListTab.size
    }

    private fun addBundle(
        fragment: Fragment,
        key: String,
        value: List<GlobalTeamRanking>
    ): Fragment {
        val valueList = GlobalTeamRankingList(listOf(
            value[0],
            value[1],
            value[2],
            value[3],
            value[4],
            value[5]
        ))
        val bundle = Bundle()
        bundle.putParcelable(key, valueList)
        fragment.arguments = bundle
        return fragment
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> addBundle(rankingListTab[position].fragment, "rankingList", ranking)
            1 -> addBundle(rankingListTab[position].fragment, "rankingList", ranking)
            else -> addBundle(rankingListTab[position].fragment, "rankingList", ranking)
        }
    }
}