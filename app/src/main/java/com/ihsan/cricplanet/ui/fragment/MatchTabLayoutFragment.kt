package com.ihsan.cricplanet.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.ihsan.cricplanet.adapter.viewpager.TabMatchAdapter
import com.ihsan.cricplanet.databinding.FragmentMatchTabLayoutBinding

class MatchTabLayoutFragment : Fragment() {
    private lateinit var binding: FragmentMatchTabLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchTabLayoutBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Tab layout
        val tabLayout = binding.tabLayoutMatch
        val viewPage = binding.viewPager2Match
        val tabMatchAdapter = TabMatchAdapter(childFragmentManager, lifecycle)
        viewPage.adapter = tabMatchAdapter
        TabLayoutMediator(tabLayout, viewPage) { tab, position ->
            tab.text = TabMatchAdapter.matchListTab[position].category
        }.attach()
    }
}