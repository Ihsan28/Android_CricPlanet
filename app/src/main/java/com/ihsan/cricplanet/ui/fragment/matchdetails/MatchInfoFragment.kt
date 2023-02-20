package com.ihsan.cricplanet.ui.fragment.matchdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ihsan.cricplanet.adapter.grid.MatchInfoGridAdapter
import com.ihsan.cricplanet.databinding.FragmentMatchInfoBinding
import com.ihsan.cricplanet.model.GridItem
import com.ihsan.cricplanet.model.fixture.FixtureByIdWithDetails
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel

class MatchInfoFragment() : Fragment() {
    private lateinit var binding:FragmentMatchInfoBinding
    var keyValueList= mutableListOf<GridItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMatchInfoBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var matchId: FixtureByIdWithDetails?
        val gridView=binding.gridView
        arguments?.let {
            matchId = it.getParcelable("matchObj")
            Toast.makeText(requireContext(), "$matchId", Toast.LENGTH_SHORT).show()
            Log.d("cricMatchInfo", "onViewCreated: ${matchId?.id}")
            if (matchId!=null){
                Toast.makeText(requireContext(), "${matchId?.id}", Toast.LENGTH_SHORT).show()
                Log.d("cricMatchInfo", "onViewCreated: ${matchId?.id}")
                keyValueList.add(GridItem("Series", "${matchId?.league?.name ?: "Not Found"}, ${matchId?.season?.name ?:""}"))
                keyValueList.add(GridItem("Match",matchId?.round ?:"Not Found"))
                keyValueList.add(GridItem("Time",Utils().dateFormat(matchId?.starting_at.toString()).toString()))
                keyValueList.add(GridItem("Toss","${matchId?.tosswon?.name ?:" Not Found"} won the toss and elected ${matchId?.elected}"))
                keyValueList.add(GridItem("Umpire(1st)","${matchId?.firstumpire?.fullname ?:" Not Found"} (getage)"))
                keyValueList.add(GridItem("Umpire(2nd)","${matchId?.secondumpire?.fullname ?:" Not Found"} (getage)"))
                keyValueList.add(GridItem("Umpire(tv)","${matchId?.tvumpire?.fullname ?:" Not Found"} (getage)"))
                keyValueList.add(GridItem("Referee","${matchId?.referee?.fullname ?:" Not Found"} (getage)"))
                keyValueList.add(GridItem("Venue",matchId?.venue?.name ?:"Not Found"))
                keyValueList.add(GridItem("Capacity",matchId?.venue?.capacity.toString()))
                keyValueList.add(GridItem("Location", matchId?.venue?.city ?:" Not Found"))
                //working
                gridView.adapter= MatchInfoGridAdapter(requireContext(),keyValueList)
                Toast.makeText(requireContext(), matchId.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        keyValueList=mutableListOf()
        Log.d("cricMatchInfo", "onPause: $keyValueList")
    }
}