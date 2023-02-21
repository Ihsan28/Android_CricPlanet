package com.ihsan.cricplanet.ui.fragment.matchdetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ihsan.cricplanet.adapter.grid.MatchSquadGridAdapter
import com.ihsan.cricplanet.databinding.FragmentMatchSquadsBinding
import com.ihsan.cricplanet.model.SquadItem
import com.ihsan.cricplanet.model.fixture.FixtureByIdWithDetails
import com.ihsan.cricplanet.model.fixture.scoreboard.lineup.Lineup

@Suppress("DEPRECATION")
class MatchSquadsFragment : Fragment() {
    private lateinit var binding: FragmentMatchSquadsBinding
    var keyValueList= mutableListOf<SquadItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMatchSquadsBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var match: FixtureByIdWithDetails?
        val gridViewLocal=binding.gridViewSquadLocal
        val gridViewVisitor=binding.gridViewSquadVisitor

            arguments?.let {
                //get mathc
                match = it.getParcelable("matchObj")
                val localTeam= match?.lineup?.filter {match?.localteam_id==it.lineup?.team_id }
                val visitorTeam= match?.lineup?.filter { match?.visitorteam_id==it.lineup?.team_id }

                Log.d("cricMatchSquad", "onViewCreated: ${localTeam?.size},${visitorTeam?.size}")
                Log.d("cricMatchInfo", "onViewCreated: ${match?.id}")
                if (match!=null){
                    //working
                    gridViewLocal.adapter= MatchSquadGridAdapter(requireContext(),localTeam as List<Lineup>)
                    gridViewVisitor.adapter= MatchSquadGridAdapter(requireContext(),visitorTeam as List<Lineup>)
                    Toast.makeText(requireContext(), match.toString(), Toast.LENGTH_SHORT).show()

                }
            }
    }
}