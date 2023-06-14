package com.ihsan.cricplanet.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.cricplanet.adapter.MatchAdapter
import com.ihsan.cricplanet.adapter.MatchAdapterSeries
import com.ihsan.cricplanet.databinding.FragmentMatchesBinding
import com.ihsan.cricplanet.utils.BottomSpaceItemDecoration
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel
import kotlinx.coroutines.launch

class MatchesFragment : Fragment() {
    private lateinit var binding: FragmentMatchesBinding
    private val viewModel: CricViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private val args: MatchesFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = Utils().progressAnimationStart(requireContext(), "Loading Matches")
        val refreshLayout = binding.swipeLayout

        // Initializing recycler view
        recyclerView = binding.recyclerviewMatches
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.addItemDecoration(BottomSpaceItemDecoration(200))
        recyclerView.setHasFixedSize(true)

        // getting the argument from navigation argument
        args.let { argument ->
            when (argument.category) {
                "UPCOMING" -> {
                    viewModel.getUpcomingFixturesApi()
                    viewModel.upcomingMatchFixture.observe(viewLifecycleOwner) {
                        Log.d("cricTeam", "onViewCreated MatchFixture: $it")
                        recyclerView.adapter = MatchAdapter(it)
                        Utils().progressAnimationStop(progressBar)
                    }
                }

                "RECENT" -> {
                    viewModel.getRecentFixturesApi()
                    viewModel.recentMatchFixture.observe(viewLifecycleOwner) {
                        Log.d("cricTeam", "onViewCreated MatchFixture: $it")
                        recyclerView.adapter = MatchAdapter(it)
                        Utils().progressAnimationStop(progressBar)
                    }
                }

                "T20" -> {
                    viewModel.getFixturesApi()
                    viewModel.matchFixture.observe(viewLifecycleOwner) {
                        val tTwentyFiltered =
                            it.filter { it1 -> it1.type == "T20" || it1.type == "T20I" }
                        Log.d("cricTeam", "onViewCreated T20 MatchFixture: ${tTwentyFiltered.size}")
                        recyclerView.adapter = MatchAdapter(tTwentyFiltered)
                        Utils().progressAnimationStop(progressBar)
                    }
                }

                "ODI" -> {
                    viewModel.getFixturesApi()
                    viewModel.matchFixture.observe(viewLifecycleOwner) {
                        val ODIFilter = it.filter { it1 -> it1.type == "ODI" }
                        Log.d("cricTeam", "onViewCreated ODI MatchFixture: ${ODIFilter.size}")
                        recyclerView.adapter = MatchAdapter(ODIFilter)
                        Utils().progressAnimationStop(progressBar)
                    }
                }

                "TEST" -> {
                    viewModel.getFixturesApi()
                    viewModel.matchFixture.observe(viewLifecycleOwner) {
                        val testFilter =
                            it.filter { it1 -> it1.type == "Test/5day" || it1.type == "4day" }
                        Log.d("cricTeam", "onViewCreated Test MatchFixture: ${testFilter.size}")
                        recyclerView.adapter = MatchAdapter(testFilter)
                        Utils().progressAnimationStop(progressBar)
                    }
                }

                "ALL" -> {
                    viewModel.getFixturesApi()
                    viewModel.matchFixture.observe(viewLifecycleOwner) {
                        Log.d("cricTeam", "onViewCreated MatchFixture: $it")
                        recyclerView.adapter = MatchAdapter(it)
                        Utils().progressAnimationStop(progressBar)
                    }
                }

                else -> {
                    argument.category?.let { viewModel.getSeasonByIdApi(it.toInt()) }
                    viewModel.seasonById.observe(viewLifecycleOwner) { season ->
                        Log.d("cricTeam", "onViewCreated MatchFixture: $season")
                        recyclerView.adapter = season.fixtures?.let { it1 ->
                            MatchAdapterSeries(
                                it1,
                                season.league?.name ?: ""
                            )
                        }
                        Utils().progressAnimationStop(progressBar)
                    }
                }
            }


            //Refreshing The Match Page
            refreshLayout.setOnRefreshListener {
                viewModel.viewModelScope.launch {
                    when (argument.category) {
                        "UPCOMING" -> viewModel.getUpcomingFixturesApi()
                        "RECENT" -> viewModel.getRecentFixturesApi()
                        "T20" -> viewModel.getFixturesApi()
                        "ODI" -> viewModel.getFixturesApi()
                        "TEST" -> viewModel.getFixturesApi()
                        "ALL" -> viewModel.getFixturesApi()
                        else -> viewModel.getFixturesApi()
                    }
                    Utils().refreshMessage()
                }
                refreshLayout.isRefreshing = false
            }
        }
    }
}