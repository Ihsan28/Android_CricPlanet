package com.ihsan.cricplanet.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.TeamRankingAdapter
import com.ihsan.cricplanet.databinding.FragmentTeamRankingsBinding
import com.ihsan.cricplanet.viewmodel.CricViewModel

class TeamRankingsFragment : Fragment() {
    private lateinit var binding:FragmentTeamRankingsBinding
    private val viewModel:CricViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentTeamRankingsBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.recyclerviewTeam
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)
        viewModel.getTeamsDB.observe(viewLifecycleOwner) {
            Log.d("cricTeam", "onViewCreated Team teamList: $it")
            recyclerView.adapter= TeamRankingAdapter(it)
            if (it.isEmpty()) {
                Log.d("cricTeam", "onViewCreated with empty roomData: APi Call ")
                viewModel.getTeams()
            }
        }
    }
}