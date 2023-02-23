package com.ihsan.cricplanet.adapter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihsan.cricplanet.model.Tab
import com.ihsan.cricplanet.model.team.GlobalTeamRanking
import com.ihsan.cricplanet.model.team.GlobalTeamRankingList
import com.ihsan.cricplanet.ui.fragment.RankingFragment

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

    private fun addBundle(
        fragment: Fragment,
        keyCategory: String,
        categoryValue: String,
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
        bundle.putString(keyCategory, categoryValue)
        fragment.arguments = bundle
        return fragment
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> addBundle(rankingListTab[position].fragment,"category",rankingListTab[position].category, "rankingList", ranking)
            1 -> addBundle(rankingListTab[position].fragment,"category",rankingListTab[position].category, "rankingList", ranking)
            else -> addBundle(rankingListTab[position].fragment,"category",rankingListTab[position].category, "rankingList", ranking)
        }
    }
}

