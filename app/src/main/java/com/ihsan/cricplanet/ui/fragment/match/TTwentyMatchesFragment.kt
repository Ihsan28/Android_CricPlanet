package com.ihsan.cricplanet.ui.fragment.match

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.cricplanet.adapter.MatchAdapter
import com.ihsan.cricplanet.databinding.FragmentTTwentyMatchesBinding
import com.ihsan.cricplanet.viewmodel.CricViewModel

class TTwentyMatchesFragment : Fragment() {
    private lateinit var binding: FragmentTTwentyMatchesBinding
    private val viewModel: CricViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentTTwentyMatchesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=binding.recyclerviewTTwentyMatches
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)
        viewModel.getFixturesApi()
        viewModel.matchFixture.observe(viewLifecycleOwner) {
            val tTwentyFiltered=it.filter { it1-> it1.type=="T20" || it1.type=="T20I" }
            Log.d("cricTeam", "onViewCreated T20 MatchFixture: ${tTwentyFiltered.size}")
            recyclerView.adapter= MatchAdapter(tTwentyFiltered)
        }
    }
}