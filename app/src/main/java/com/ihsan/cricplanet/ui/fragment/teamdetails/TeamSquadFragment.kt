package com.ihsan.cricplanet.ui.fragment.teamdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ihsan.cricplanet.adapter.grid.TeamSquadAdapter
import com.ihsan.cricplanet.databinding.FragmentTeamSquadBinding
import com.ihsan.cricplanet.model.team.TeamDetails

@Suppress("DEPRECATION")
class TeamSquadFragment : Fragment() {
    private lateinit var binding: FragmentTeamSquadBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTeamSquadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var team: TeamDetails?
        val gridViewTeam = binding.gridViewSquadTeam

        arguments.let {
            if (it != null) {
                team = it.getParcelable("team")

                if (team != null) {
                    //set adapter
                    gridViewTeam.adapter =
                        team!!.squad?.let { it1 -> TeamSquadAdapter(requireContext(), it1) }
                }
            }
        }

    }
}