package com.ihsan.cricplanet.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihsan.cricplanet.model.Tab
import com.ihsan.cricplanet.model.fixture.FixtureByIdWithDetails
import com.ihsan.cricplanet.ui.fragment.matchdetails.MatchInfoFragment
import com.ihsan.cricplanet.ui.fragment.matchdetails.MatchScorecardFragment
import com.ihsan.cricplanet.ui.fragment.matchdetails.MatchSquadsFragment
import com.ihsan.cricplanet.utils.Utils

class TabMatchDetailAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle,
    private val match: FixtureByIdWithDetails
) : FragmentStateAdapter(manager, lifecycle) {

    val listMatchDetailTab = mutableListOf(
        Tab(MatchInfoFragment(), "INFO"),
        Tab(MatchSquadsFragment(), "SQUADS"),
        Tab(MatchScorecardFragment(), "SCORECARD")
    )

    override fun getItemCount(): Int {
        return listMatchDetailTab.size
    }

    override fun createFragment(position: Int): Fragment {
        return Utils().addBundle(listMatchDetailTab[position].fragment, "match", match)
    }
}