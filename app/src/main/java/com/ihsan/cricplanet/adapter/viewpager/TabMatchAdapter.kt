package com.ihsan.cricplanet.adapter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihsan.cricplanet.model.Tab
import com.ihsan.cricplanet.ui.fragment.MatchesFragment

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

    private fun addBundle(
        fragment: Fragment,
        keyCategory: String,
        categoryValue: String
    ): Fragment {
        val bundle = Bundle()
        bundle.putString(keyCategory, categoryValue)
        fragment.arguments = bundle
        return fragment
    }

    override fun getItemCount(): Int {
        return matchListTab.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> addBundle(
                matchListTab[position].fragment,
                "category",
                matchListTab[position].category
            )
            1 -> addBundle(
                matchListTab[position].fragment,
                "category",
                matchListTab[position].category
            )
            2 -> addBundle(
                matchListTab[position].fragment,
                "category",
                matchListTab[position].category
            )
            3 -> addBundle(
                matchListTab[position].fragment,
                "category",
                matchListTab[position].category
            )
            4 -> addBundle(
                matchListTab[position].fragment,
                "category",
                matchListTab[position].category
            )
            5 -> addBundle(
                matchListTab[position].fragment,
                "category",
                matchListTab[position].category
            )
            else -> addBundle(
                matchListTab[position].fragment,
                "category",
                matchListTab[position].category
            )
        }
    }
}