package com.ihsan.cricplanet.ui.fragment.playerdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihsan.cricplanet.adapter.PlayerCareerTeamAdapter
import com.ihsan.cricplanet.adapter.TeamRankingAdapter
import com.ihsan.cricplanet.databinding.FragmentPlayerCareerBinding
import com.ihsan.cricplanet.viewmodel.CricViewModel

class PlayerCareerFragment : Fragment() {
    private val args: PlayerCareerFragmentArgs by navArgs()
    private lateinit var binding: FragmentPlayerCareerBinding
    private val viewmodel:CricViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayerCareerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewCareer = binding.recyclerviewPlayerCareer

        recyclerViewCareer.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerViewCareer.setHasFixedSize(true)

        args.let { playerArgs ->
            //get team
            recyclerViewCareer.adapter = playerArgs.player?.teams?.let { PlayerCareerTeamAdapter(it,viewmodel,viewLifecycleOwner) }
        }
    }

}