package com.ihsan.cricplanet.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihsan.cricplanet.model.Tab
import com.ihsan.cricplanet.model.team.TeamDetails
import com.ihsan.cricplanet.ui.fragment.teamdetails.TeamFixturesFragment
import com.ihsan.cricplanet.ui.fragment.teamdetails.TeamSquadFragment
import com.ihsan.cricplanet.utils.Utils

class TabTeamDetailAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle,
    private val team: TeamDetails
) : FragmentStateAdapter(manager, lifecycle) {

    val listTeamDetailTab = listOf(
        Tab(TeamSquadFragment(), "SQUAD"),
        Tab(TeamFixturesFragment(), "CAREER")
    )

    override fun getItemCount(): Int {
        return listTeamDetailTab.size
    }

    override fun createFragment(position: Int): Fragment {
        return Utils().addBundle(listTeamDetailTab[position].fragment, "team", team)
    }
}