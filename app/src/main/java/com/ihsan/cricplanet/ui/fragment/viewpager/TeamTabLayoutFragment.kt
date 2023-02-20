package com.ihsan.cricplanet.ui.fragment.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.viewpager.TabTeamAdapter
import com.ihsan.cricplanet.databinding.FragmentTeamTabLayoutBinding

class TeamTabLayoutFragment : Fragment() {
    private lateinit var binding: FragmentTeamTabLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamTabLayoutBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Tab layout
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout_team)
        val viewPage = view.findViewById<ViewPager2>(R.id.view_pager_2_team)
        val tabTeamAdapter = TabTeamAdapter(childFragmentManager, lifecycle)
        viewPage.adapter = tabTeamAdapter
        TabLayoutMediator(tabLayout, viewPage) { tab, position ->
            tab.text = TabTeamAdapter.teamListTab[position].category
        }.attach()
    }
}