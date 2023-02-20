package com.ihsan.cricplanet.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.cricplanet.adapter.TeamAdapter
import com.ihsan.cricplanet.databinding.FragmentTeamBinding
import com.ihsan.cricplanet.viewmodel.CricViewModel

class TeamFragment : Fragment() {
    private lateinit var binding: FragmentTeamBinding
    private val viewModel: CricViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentTeamBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerviewTeam
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)
        viewModel.getTeamsDB.observe(viewLifecycleOwner) {
            Log.d("cricTeam", "onViewCreated Team teamList: $it")
            recyclerView.adapter=TeamAdapter(it)
            if (it.isEmpty()) {
                Log.d("cricTeam", "onViewCreated with empty roomData: APi Call ")
                viewModel.getTeams()
            }
        }
    }
}