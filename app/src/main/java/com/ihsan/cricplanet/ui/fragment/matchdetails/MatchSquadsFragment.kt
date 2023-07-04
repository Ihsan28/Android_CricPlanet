package com.ihsan.cricplanet.ui.fragment.matchdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ihsan.cricplanet.adapter.grid.MatchSquadAdapter
import com.ihsan.cricplanet.databinding.FragmentMatchSquadsBinding
import com.ihsan.cricplanet.model.fixture.FixtureByIdWithDetails
import com.ihsan.cricplanet.model.fixture.scoreboard.lineup.Lineup

class MatchSquadsFragment : Fragment() {
    private lateinit var binding: FragmentMatchSquadsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMatchSquadsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var match: FixtureByIdWithDetails?
        val gridViewLocal = binding.gridViewSquadLocal
        val gridViewVisitor = binding.gridViewSquadVisitor

        arguments.let {
            if (it != null) {
                match = it.getParcelable("match")
                val localTeam = match?.lineup?.filter { match?.localteam_id == it.lineup?.team_id }
                val visitorTeam =
                    match?.lineup?.filter { match?.visitorteam_id == it.lineup?.team_id }

                Log.d("cricMatchSquad", "onViewCreated: ${localTeam?.size},${visitorTeam?.size}")
                Log.d("cricMatchInfo", "onViewCreated: ${match?.id}")
                if (match != null) {
                    //set adapter
                    gridViewLocal.adapter =
                        MatchSquadAdapter(requireContext(), localTeam as List<Lineup>)
                    gridViewVisitor.adapter =
                        MatchSquadAdapter(requireContext(), visitorTeam as List<Lineup>)
                }
            }
        }
    }
}