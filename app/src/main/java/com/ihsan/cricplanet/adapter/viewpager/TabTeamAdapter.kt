package com.ihsan.cricplanet.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihsan.cricplanet.model.Tab
import com.ihsan.cricplanet.ui.fragment.*

class TabTeamAdapter (manager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(manager,lifecycle) {
    companion object{
        val teamListTab = listOf(
            Tab(TeamFragment(), "All"),
        )
    }
    override fun getItemCount(): Int {
        return  teamListTab.size
    }

    override fun createFragment(position: Int): Fragment {
        return teamListTab[position].fragment
    }
}