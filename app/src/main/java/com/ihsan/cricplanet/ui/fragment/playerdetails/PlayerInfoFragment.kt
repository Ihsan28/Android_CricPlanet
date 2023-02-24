package com.ihsan.cricplanet.ui.fragment.playerdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ihsan.cricplanet.adapter.grid.MatchInfoGridAdapter
import com.ihsan.cricplanet.databinding.FragmentPlayerInfoBinding
import com.ihsan.cricplanet.model.GridItem
import com.ihsan.cricplanet.model.player.PlayerDetails
import com.ihsan.cricplanet.utils.Utils

class PlayerInfoFragment : Fragment() {
    private lateinit var binding: FragmentPlayerInfoBinding
    var keyValueList = mutableListOf<GridItem>()
    private val args: PlayerInfoFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayerInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val player:PlayerDetails
        val gridView = binding.playerInfoGridView
        args.let {
            val utils=Utils()
            player=it.player!!
            if (player!=null){
                Log.d("cricPlayerInfo", "onViewCreated: ${player.id}")
                keyValueList.add(GridItem("Born", "${player.dateofbirth?.let { it1 -> utils.getPlayerBorn(it1) }}"))

            }

            //working
            gridView.adapter = MatchInfoGridAdapter(requireContext(), keyValueList)
        }
    }

    override fun onPause() {
        super.onPause()
        keyValueList = mutableListOf()
    }

}