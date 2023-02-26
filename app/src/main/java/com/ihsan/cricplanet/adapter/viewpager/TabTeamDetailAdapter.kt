package com.ihsan.cricplanet.adapter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihsan.cricplanet.model.Tab
import com.ihsan.cricplanet.model.team.TeamDetails
import com.ihsan.cricplanet.ui.fragment.teamdetails.TeamFixturesFragment
import com.ihsan.cricplanet.ui.fragment.teamdetails.TeamSquadFragment

class TabTeamDetailAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle,
    private val team: TeamDetails
) : FragmentStateAdapter(manager, lifecycle) {
    companion object {
        val listTeamDetailTab = listOf(
            Tab(TeamSquadFragment(), "SQUAD"),
            Tab(TeamFixturesFragment(), "CAREER")
        )
    }

    private fun addBundle(fragment: Fragment, key:String, value: TeamDetails): Fragment {
        val bundle = Bundle()
        bundle.putParcelable(key, value)
        fragment.arguments = bundle
        return fragment
    }

    override fun getItemCount(): Int {
        return listTeamDetailTab.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->addBundle(listTeamDetailTab[position].fragment,"team",team)
            else->addBundle(listTeamDetailTab[position].fragment,"team",team)
        }
    }
}