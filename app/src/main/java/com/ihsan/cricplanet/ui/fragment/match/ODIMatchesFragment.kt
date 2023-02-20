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
import com.ihsan.cricplanet.databinding.FragmentODIMatchesBinding
import com.ihsan.cricplanet.viewmodel.CricViewModel

class ODIMatchesFragment : Fragment() {
    private lateinit var binding: FragmentODIMatchesBinding
    private val viewModel: CricViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentODIMatchesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=binding.recyclerviewODIMatches
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)
        viewModel.getFixturesApi()
        viewModel.matchFixture.observe(viewLifecycleOwner) {
            val ODIFilter=it.filter { it1-> it1.type=="ODI" }
            Log.d("cricTeam", "onViewCreated ODI MatchFixture: ${ODIFilter.size}")
            recyclerView.adapter= MatchAdapter(ODIFilter)
        }
    }
}