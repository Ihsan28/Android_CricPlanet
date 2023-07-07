package com.ihsan.cricplanet.ui.fragment.matchdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihsan.cricplanet.adapter.BattingScorecardAdapter
import com.ihsan.cricplanet.adapter.MatchBallsAdapter
import com.ihsan.cricplanet.databinding.FragmentMatchScorecardBinding
import com.ihsan.cricplanet.model.fixture.FixtureByIdWithDetails

@Suppress("DEPRECATION")
class MatchScorecardFragment : Fragment() {
    private lateinit var binding: FragmentMatchScorecardBinding
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


        arguments.let {
            if (it != null) {
                match = it.getParcelable("match")
                recyclerViewBatting.adapter =
                    match?.batting?.let { it1 -> BattingScorecardAdapter(it1) }

                //Recycler view for Balls or Match History
                val recyclerViewUpcoming = binding.recyclerviewBalls
                recyclerViewUpcoming.layoutManager =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                recyclerViewUpcoming.setHasFixedSize(true)
                recyclerViewUpcoming.adapter =
                    match?.balls?.let { it1 -> MatchBallsAdapter(it1.take(20)) }
            }
        }
    }
}