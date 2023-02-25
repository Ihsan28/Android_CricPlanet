package com.ihsan.cricplanet.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.MatchAdapterHome
import com.ihsan.cricplanet.adapter.PlayerAdapter
import com.ihsan.cricplanet.databinding.FragmentPlayerBinding
import com.ihsan.cricplanet.model.player.PlayerCard
import com.ihsan.cricplanet.viewmodel.CricViewModel

class PlayerFragment : Fragment() {
    private lateinit var binding:FragmentPlayerBinding
    private val viewModel: CricViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentPlayerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.recyclerviewPlayer
        var player:List<PlayerCard>?=null
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)

        viewModel.getPlayersApi()
        viewModel.player.observe(viewLifecycleOwner) {
            Log.d("cricPlayer", "onViewCreated PLAYER: $it")
            recyclerView.adapter = PlayerAdapter(it)
            player=it
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
    }
}