package com.ihsan.cricplanet.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihsan.cricplanet.model.Tab
import com.ihsan.cricplanet.ui.fragment.HomeFragment

class TabAdapter(manager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(manager,lifecycle) {
    companion object{
        val listTab = listOf(
            Tab(HomeFragment(), "Home")
        )
    }
    override fun getItemCount(): Int {
        return  listTab.size
    }

    override fun createFragment(position: Int): Fragment {
        return listTab[position].fragment
    }
}