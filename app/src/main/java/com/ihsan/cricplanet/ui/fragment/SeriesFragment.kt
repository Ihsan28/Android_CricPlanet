package com.ihsan.cricplanet.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihsan.cricplanet.adapter.LeagueAdapter
import com.ihsan.cricplanet.databinding.FragmentSeriesBinding
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel
import kotlinx.coroutines.launch

class SeriesFragment : Fragment() {
    private lateinit var binding: FragmentSeriesBinding
    private val viewModel: CricViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Refresh Layout
        val refreshLayout = binding.swipeLayout

        //Recycler view
        val recyclerView = binding.recyclerviewLeague
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)

        //getting data for league from Api
        viewModel.getLeagueApi()
        viewModel.league.observe(viewLifecycleOwner) {
            Log.d("cricleague", "onViewCreated league: $it")
            recyclerView.adapter = LeagueAdapter(it, requireActivity())
        }

        //Refreshing The Home Page
        refreshLayout.setOnRefreshListener {
            viewModel.viewModelScope.launch {
                //getting data for league from Api
                viewModel.getLeagueApi()
                Utils().refreshMessage()
            }
            refreshLayout.isRefreshing = false
        }
    }
}