package com.ihsan.cricplanet.ui.fragment.matchdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ihsan.cricplanet.adapter.grid.MatchInfoGridAdapter
import com.ihsan.cricplanet.databinding.FragmentMatchInfoBinding
import com.ihsan.cricplanet.model.GridItem
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel

class MatchInfoFragment() : Fragment() {
    private val viewmodel: CricViewModel by viewModels()
    private lateinit var binding:FragmentMatchInfoBinding
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
        var matchId: Int? = 0
        val gridView=binding.gridView
        arguments?.let {
            matchId = it.getString("matchId")?.toInt()

            Toast.makeText(requireContext(), it.getString("matchId"), Toast.LENGTH_SHORT).show()
        }

        viewmodel.fixtureByIdWithDetails.observe(viewLifecycleOwner){
            val keyValueList= mutableListOf<GridItem>()
            keyValueList.add(GridItem("Series", "${it.league?.name ?: "Not Found"}, ${it.season?.name ?:""}"))
            keyValueList.add(GridItem("Match",it.round ?:"Not Found"))
            keyValueList.add(GridItem("Time",Utils().dateFormat(it.starting_at.toString()).toString()))
            keyValueList.add(GridItem("Toss","${it.tosswon?.name ?:" Not Found"} won the toss and elected ${it.elected}"))
            keyValueList.add(GridItem("Umpire(1st)","${it.firstumpire?.fullname ?:" Not Found"} (getage)"))
            keyValueList.add(GridItem("Umpire(2nd)","${it.secondumpire?.fullname ?:" Not Found"} (getage)"))
            keyValueList.add(GridItem("Umpire(tv)","${it.tvumpire?.fullname ?:" Not Found"} (getage)"))
            keyValueList.add(GridItem("Referee","${it.referee?.fullname ?:" Not Found"} (getage)"))
            keyValueList.add(GridItem("Venue",it.venue?.name ?:"Not Found"))
            keyValueList.add(GridItem("Capacity",it.venue?.capacity.toString()))
            keyValueList.add(GridItem("Location","${it.venue?.city ?:" Not Found"}"))
            //working
            gridView.adapter= MatchInfoGridAdapter(requireContext(),keyValueList)
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}