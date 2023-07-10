package com.ihsan.cricplanet.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihsan.cricplanet.adapter.PlayerAdapter
import com.ihsan.cricplanet.databinding.FragmentPlayerBinding
import com.ihsan.cricplanet.model.player.PlayerCard
import com.ihsan.cricplanet.utils.CheckNetwork
import com.ihsan.cricplanet.utils.CheckNetwork.Companion.networkStatus
import com.ihsan.cricplanet.utils.MyApplication
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel
import kotlinx.coroutines.launch

class PlayerFragment : Fragment() {
    private lateinit var binding: FragmentPlayerBinding
    private val viewModel: CricViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        networkStatus.observe(viewLifecycleOwner) {
            if (it.connection) {
                refreshPlayer()
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var player: List<PlayerCard>? = null
        //Refresh Layout
        val refreshLayout = binding.swipeLayout

        //recycler view
        val recyclerView = binding.recyclerviewPlayer
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)

        viewModel.getPlayersApi()
        val progressBar = Utils().progressAnimationStart(requireContext(), "Loading Player")
        viewModel.player.observe(viewLifecycleOwner) {
            Log.d("cricPlayer", "onViewCreated PLAYER: $it")
            recyclerView.adapter = PlayerAdapter(it)
            player = it
            Utils().progressAnimationStop(progressBar)
        }

        val searchView = binding.playerSearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle search query submission
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty() && !player.isNullOrEmpty()) {
                    val adapter = recyclerView.adapter as PlayerAdapter
                    adapter.filter(newText)
                }
                // Handle search query text change
                return true
            }
        })

        //Refreshing The Home Page
        refreshLayout.setOnRefreshListener {
            refreshPlayer()
            refreshLayout.isRefreshing = false
        }
    }

    private fun refreshPlayer() {
        viewModel.viewModelScope.launch {
            viewModel.getPlayersApi()
        }
    }
}