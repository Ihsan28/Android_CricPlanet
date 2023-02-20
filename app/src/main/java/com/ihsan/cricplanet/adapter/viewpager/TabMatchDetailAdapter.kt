package com.ihsan.cricplanet.adapter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihsan.cricplanet.model.Tab
import com.ihsan.cricplanet.ui.fragment.matchdetails.MatchInfoFragment
import com.ihsan.cricplanet.ui.fragment.matchdetails.MatchScorecardFragment
import com.ihsan.cricplanet.ui.fragment.matchdetails.MatchSquadsFragment

class TabMatchDetailAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle,
    private val matchId: Int
) : FragmentStateAdapter(manager, lifecycle) {
    companion object {
        val listMatchDetailTab = listOf(
            Tab(MatchInfoFragment(), "INFO"),
            Tab(MatchSquadsFragment(), "SQUADS"),
            Tab(MatchScorecardFragment(), "SCORECARD")
        )
    }

    override fun getItemCount(): Int {
        return listMatchDetailTab.size
    }

    private fun addBundle(fragment: Fragment, key:String): Fragment {
        val bundle = Bundle()
        bundle.putString("matchId", key)
        fragment.arguments = bundle
        return fragment
    }

    override fun createFragment(position: Int): Fragment {
        listMatchDetailTab.map { addBundle(it.fragment,matchId.toString()) }
        return listMatchDetailTab[position].fragment
    }
}