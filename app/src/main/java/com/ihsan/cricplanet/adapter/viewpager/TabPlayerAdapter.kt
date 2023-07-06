package com.ihsan.cricplanet.adapter.viewpager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihsan.cricplanet.model.Tab
import com.ihsan.cricplanet.model.player.PlayerDetails
import com.ihsan.cricplanet.ui.fragment.playerdetails.PlayerBattingBowlingFragment
import com.ihsan.cricplanet.ui.fragment.playerdetails.PlayerCareerFragment
import com.ihsan.cricplanet.ui.fragment.playerdetails.PlayerInfoFragment
import com.ihsan.cricplanet.utils.Utils

class TabPlayerAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle,
    val player: PlayerDetails
) : FragmentStateAdapter(manager, lifecycle) {

    var listPlayerDetailTab = mutableListOf(
        Tab(PlayerInfoFragment(), "Info"),
        Tab(PlayerBattingBowlingFragment(true), "Batting"),
        Tab(PlayerBattingBowlingFragment(false), "Bowling"),
        Tab(PlayerCareerFragment(), "Career")
    )

    override fun getItemCount(): Int {
        return listPlayerDetailTab.size
    }

    override fun createFragment(position: Int): Fragment {
        Log.d("cricTabPlayerAdapter", "cricTabPlayerAdaptercreateFragmentPosition: $position")
        return Utils().addBundle(listPlayerDetailTab[position].fragment,"player", player)
    }
}