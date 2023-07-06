package com.ihsan.cricplanet.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihsan.cricplanet.model.Tab
import com.ihsan.cricplanet.ui.fragment.MatchesFragment
import com.ihsan.cricplanet.utils.Utils

class TabMatchAdapter(manager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(manager, lifecycle) {
    companion object {
        val matchListTab = listOf(
            Tab(MatchesFragment(), "UPCOMING"),
            Tab(MatchesFragment(), "RECENT"),
            Tab(MatchesFragment(), "T20"),
            Tab(MatchesFragment(), "ODI"),
            Tab(MatchesFragment(), "TEST"),
            Tab(MatchesFragment(), "ALL")
        )
    }

    override fun getItemCount(): Int {
        return matchListTab.size
    }

    override fun createFragment(position: Int): Fragment {
        return Utils().addBundle(
            matchListTab[position].fragment,
            "category",
            matchListTab[position].category
        )
    }
}