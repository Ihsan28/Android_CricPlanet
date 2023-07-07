package com.ihsan.cricplanet.ui.fragment.matchdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ihsan.cricplanet.adapter.grid.MatchInfoAdapter
import com.ihsan.cricplanet.databinding.FragmentMatchInfoBinding
import com.ihsan.cricplanet.model.GridItem
import com.ihsan.cricplanet.model.fixture.FixtureByIdWithDetails
import com.ihsan.cricplanet.utils.Utils

class MatchInfoFragment : Fragment() {
    private lateinit var binding: FragmentMatchInfoBinding
    var keyValueList = mutableListOf<GridItem>()
    private val args: MatchInfoFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchInfoBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var match: FixtureByIdWithDetails?
        val gridView = binding.gridView
        arguments.let {
            if (it != null) {
                match = it.getParcelable("match")
                Log.d("cricMatchInfo", "onViewCreated: ${match?.id}")
                if (match != null) {
                    Log.d("cricMatchInfo", "onViewCreated: ${match?.id}")
                    keyValueList.add(
                        GridItem(
                            "Series",
                            "${match?.league?.name ?: "Not Found"}, ${match?.season?.name ?: ""}"
                        )
                    )
                    keyValueList.add(GridItem("Match", match?.round ?: "Not Found"))
                    keyValueList.add(
                        GridItem(
                            "Time",
                            Utils().dateFormat(match?.starting_at.toString()).toString()
                        )
                    )
                    keyValueList.add(
                        GridItem(
                            "Toss",
                            "${match?.tosswon?.name ?: " Not Found"} won the toss and elected ${match?.elected}"
                        )
                    )
                    keyValueList.add(
                        GridItem(
                            "Umpire(1st)",
                            "${match?.firstumpire?.fullname ?: " Not Found"} (getage)"
                        )
                    )
                    keyValueList.add(
                        GridItem(
                            "Umpire(2nd)",
                            "${match?.secondumpire?.fullname ?: " Not Found"} (getage)"
                        )
                    )
                    keyValueList.add(
                        GridItem(
                            "Umpire(tv)",
                            "${match?.tvumpire?.fullname ?: " Not Found"} (getage)"
                        )
                    )
                    keyValueList.add(
                        GridItem(
                            "Referee",
                            "${match?.referee?.fullname ?: " Not Found"} (getage)"
                        )
                    )
                    keyValueList.add(GridItem("Venue", match?.venue?.name ?: "Not Found"))
                    keyValueList.add(GridItem("Capacity", match?.venue?.capacity.toString()))
                    keyValueList.add(GridItem("Location", match?.venue?.city ?: " Not Found"))

                    //Grid adapter call for table
                    gridView.adapter = MatchInfoAdapter(requireContext(), keyValueList)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        keyValueList = mutableListOf()
        Log.d("cricMatchInfo", "onPause: $keyValueList")
    }
}