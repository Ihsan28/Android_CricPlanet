package com.ihsan.cricplanet.ui.fragment.playerdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihsan.cricplanet.adapter.PlayerCareerTeamAdapter
import com.ihsan.cricplanet.databinding.FragmentPlayerCareerBinding
import com.ihsan.cricplanet.model.player.PlayerDetails
import com.ihsan.cricplanet.viewmodel.CricViewModel

@Suppress("DEPRECATION")
class PlayerCareerFragment : Fragment() {
    private val args: PlayerCareerFragmentArgs by navArgs()
    private lateinit var binding: FragmentPlayerCareerBinding
    private val viewmodel: CricViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayerCareerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewCareer = binding.recyclerviewPlayerCareer
        val player: PlayerDetails?
        recyclerViewCareer.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerViewCareer.setHasFixedSize(true)

        arguments.let {
            if (it != null) {
                player = it.getParcelable("player")
                Log.d("cricPlayerCareer", "onViewCreated: ${player}")
                recyclerViewCareer.adapter = player?.teams?.let {listTeam->
                    PlayerCareerTeamAdapter(
                        listTeam, viewmodel, viewLifecycleOwner
                    )
                }
            }

        }
    }
}