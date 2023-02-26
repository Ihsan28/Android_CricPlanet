package com.ihsan.cricplanet.ui.fragment.teamdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.cricplanet.adapter.MatchAdapterSeries
import com.ihsan.cricplanet.adapter.TeamMatchAdapter
import com.ihsan.cricplanet.databinding.FragmentTeamFixturesBinding
import com.ihsan.cricplanet.model.team.TeamDetails
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class TeamFixturesFragment : Fragment() {
    private lateinit var binding: FragmentTeamFixturesBinding
    private val viewModel: CricViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTeamFixturesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initializing recycler view
        recyclerView = binding.recyclerviewMatches
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)

        var team:TeamDetails
        // getting the argument from navigation argument
        arguments.let {
            if (it != null) {
                team= it.getParcelable("team")!!
                recyclerView.adapter = team.results?.let { it1 -> TeamMatchAdapter(it1) }
            }
        }
    }
}