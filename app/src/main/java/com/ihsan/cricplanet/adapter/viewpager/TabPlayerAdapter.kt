package com.ihsan.cricplanet.adapter.viewpager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihsan.cricplanet.model.Tab
import com.ihsan.cricplanet.model.player.PlayerDetails
import com.ihsan.cricplanet.ui.fragment.playerdetails.PlayerBattingFragment
import com.ihsan.cricplanet.ui.fragment.playerdetails.PlayerBowlingFragment
import com.ihsan.cricplanet.ui.fragment.playerdetails.PlayerCareerFragment
import com.ihsan.cricplanet.ui.fragment.playerdetails.PlayerInfoFragment

class TabPlayerAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle,
    private val player: PlayerDetails
) : FragmentStateAdapter(manager, lifecycle) {

    var listPlayerDetailTab = mutableListOf(
        Tab(PlayerInfoFragment(), "Info"),
        Tab(PlayerBattingFragment(), "Batting"),
        Tab(PlayerBowlingFragment(), "Bowling"),
        Tab(PlayerCareerFragment(), "Career")
    )


    private fun addBundle(fragment: Fragment): Fragment {
        val bundle = Bundle()
        Log.d("cricTabPlayerAdapter", "cricTabPlayerAdapterAddBundle: ${player.fullname}")
        bundle.putParcelable("player", player)
        fragment.arguments = bundle
        return fragment
    }

    override fun getItemCount(): Int {
        return listPlayerDetailTab.size
    }

    override fun createFragment(position: Int): Fragment {
        Log.d("cricTabPlayerAdapter", "cricTabPlayerAdaptercreateFragmentPosition: $position")
        return addBundle(listPlayerDetailTab[position].fragment)
    }
}