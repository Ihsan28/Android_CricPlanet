package com.ihsan.cricplanet.adapter.viewpager

import android.os.Bundle
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
    companion object {
        val listPlayerDetailTab = listOf(
            Tab(PlayerInfoFragment(), "Info"),
            Tab(PlayerBattingFragment(), "Batting"),
            Tab(PlayerBowlingFragment(), "Bowling"),
            Tab(PlayerCareerFragment(), "Career")
        )
    }

    private fun addBundle(fragment: Fragment, key: String, value: PlayerDetails): Fragment {
        val bundle = Bundle()
        bundle.putParcelable(key, value)
        fragment.arguments = bundle
        return fragment
    }

    override fun getItemCount(): Int {
        return listPlayerDetailTab.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> addBundle(listPlayerDetailTab[position].fragment, "player", player)
            1 -> addBundle(listPlayerDetailTab[position].fragment, "player", player)
            else -> addBundle(listPlayerDetailTab[position].fragment, "player", player)
        }
    }
}