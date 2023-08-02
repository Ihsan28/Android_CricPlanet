package com.ihsan.cricplanet.ui.fragment.matchdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihsan.cricplanet.adapter.BattingScorecardAdapter
import com.ihsan.cricplanet.adapter.BowlingScorecardAdapter
import com.ihsan.cricplanet.adapter.MatchBallsAdapter
import com.ihsan.cricplanet.databinding.FragmentMatchScorecardBinding
import com.ihsan.cricplanet.model.fixture.FixtureByIdWithDetails
import com.ihsan.cricplanet.ui.fragment.viewpagertab.callBackInterface.DetailsTabLayoutFragmentCallback

@Suppress("DEPRECATION")
class MatchScorecardFragment : Fragment() {
    private lateinit var binding: FragmentMatchScorecardBinding
    var parentFragmentCallback: DetailsTabLayoutFragmentCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMatchScorecardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var match: FixtureByIdWithDetails?

        val recyclerViewBatting = binding.recyclerviewBattingScore
        recyclerViewBatting.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerViewBatting.setHasFixedSize(true)

        val recyclerViewBowling = binding.recyclerviewBowlingScore
        recyclerViewBowling.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerViewBowling.setHasFixedSize(true)

        arguments.let {
            if (it != null) {
                match = it.getParcelable("match")
                recyclerViewBatting.adapter =
                    match?.batting?.let { battingIncludeBatsmanList ->
                        BattingScorecardAdapter(
                            battingIncludeBatsmanList
                        )
                    }

                recyclerViewBowling.adapter =
                    match?.bowling?.let { bowlingIncludeBatsmanList ->
                        BowlingScorecardAdapter(
                            bowlingIncludeBatsmanList
                        )
                    }

                val recyclerViewScorecard = binding.recyclerviewBalls
                if (match?.balls != null && match?.balls?.size!! > 0) {
                    //Recycler view for Balls or Match History
                    recyclerViewScorecard.layoutManager =
                        LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                    recyclerViewScorecard.setHasFixedSize(true)

                    recyclerViewScorecard.adapter =
                        match?.balls?.let { it1 -> MatchBallsAdapter(it1.take(20)) }
                } else {
                    recyclerViewScorecard.visibility = View.GONE
                }
            }
        }
    }
}