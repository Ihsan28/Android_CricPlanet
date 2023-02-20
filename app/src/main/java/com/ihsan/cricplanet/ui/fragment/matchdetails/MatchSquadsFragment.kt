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
import com.ihsan.cricplanet.model.GridItem
import com.ihsan.cricplanet.model.SquadItem
import com.ihsan.cricplanet.model.fixture.FixtureByIdWithDetails
import com.ihsan.cricplanet.model.fixture.scoreboard.lineup.Lineup
import com.ihsan.cricplanet.model.player.Player
import com.ihsan.cricplanet.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
        val gridView=binding.gridViewSquad

            arguments?.let {
                match = it.getParcelable("matchObj")
                var localTeam:MutableList<Lineup>?= mutableListOf()
                var visitorTeam= mutableListOf<Lineup>()
                match?.lineup?.map {it
                    if (match?.localteam_id==it.lineup?.team_id){
                        localTeam?.add(it)
                    }else{
                        visitorTeam.add(it)
                    }
                }
                Log.d("cricMatchSquad", "onViewCreated: ${localTeam?.size},${visitorTeam.size}")

                Toast.makeText(requireContext(), "$match", Toast.LENGTH_SHORT).show()
                Log.d("cricMatchInfo", "onViewCreated: ${match?.id}")
                if (match!=null){
                    //working
                    gridView.adapter= MatchSquadGridAdapter(requireContext(),localTeam as List<Lineup>)
                    Toast.makeText(requireContext(), match.toString(), Toast.LENGTH_SHORT).show()
                }
            }

    }
}