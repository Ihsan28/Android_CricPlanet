package com.ihsan.cricplanet.ui.fragment.playerdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ihsan.cricplanet.adapter.grid.PlayerDetailsGridAdapter
import com.ihsan.cricplanet.databinding.FragmentPlayerBattingBinding
import com.ihsan.cricplanet.model.PlayerGridItem
import com.ihsan.cricplanet.model.player.PlayerDetails
import com.ihsan.cricplanet.utils.Utils

class PlayerBattingFragment : Fragment() {
    private lateinit var binding: FragmentPlayerBattingBinding
    private var keyValueList = mutableListOf<PlayerGridItem>()
    private val args: PlayerBattingFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayerBattingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val player: PlayerDetails
        val gridView = binding.playerBattingGridView
        args.let {
            player=it.player!!
            if (player!=null){
                Log.d("cricPlayerInfo", "onViewCreated: ${player.id}")
                val test= player.career?.get(0)?.batting
                val t20= player.career?.get(1)?.batting
                val odi= player.career?.get(2)?.batting
                val league= player.career?.get(3)?.batting
                keyValueList.add(PlayerGridItem("Matches", (test?.matches ?:0).toString(),(t20?.matches ?:0).toString(),(odi?.matches ?:0).toString(),(league?.matches ?:0).toString() ))
                keyValueList.add(PlayerGridItem("Innings", (test?.innings ?:0).toString(),(t20?.innings ?:0).toString(),(odi?.innings ?:0).toString(),(league?.innings ?:0).toString()))
                keyValueList.add(PlayerGridItem("Runs", (test?.runs_scored ?:0).toString(),(t20?.runs_scored ?:0).toString(),(odi?.runs_scored ?:0).toString(),(league?.runs_scored ?:0).toString()))
                keyValueList.add(PlayerGridItem("Not Out", (test?.not_outs ?:0).toString(),(t20?.not_outs ?:0).toString(),(odi?.not_outs ?:0).toString(),(league?.not_outs ?:0).toString()))
                keyValueList.add(PlayerGridItem("Highest", (test?.highest_inning_score ?:0).toString(),(t20?.highest_inning_score ?:0).toString(),(odi?.highest_inning_score ?:0).toString(),(league?.highest_inning_score ?:0).toString()))
                keyValueList.add(PlayerGridItem("SR", (test?.strike_rate ?:0).toString(),(t20?.strike_rate ?:0).toString(),(odi?.strike_rate ?:0).toString(),(league?.strike_rate ?:0).toString()))
                keyValueList.add(PlayerGridItem("Balls", (test?.balls_faced ?:0).toString(),(t20?.balls_faced ?:0).toString(),(odi?.balls_faced ?:0).toString(),(league?.balls_faced ?:0).toString()))
                keyValueList.add(PlayerGridItem("Average", (test?.average ?:0).toString(),(t20?.average ?:0).toString(),(odi?.average ?:0).toString(),(league?.average ?:0).toString()))
                keyValueList.add(PlayerGridItem("Fours", (test?.four_x ?:0).toString(),(t20?.four_x ?:0).toString(),(odi?.four_x ?:0).toString(),(league?.four_x ?:0).toString()))
                keyValueList.add(PlayerGridItem("Sixes", (test?.six_x ?:0).toString(),(t20?.six_x ?:0).toString(),(odi?.six_x ?:0).toString(),(league?.six_x ?:0).toString()))
                keyValueList.add(PlayerGridItem("50s", (test?.fifties ?:0).toString(),(t20?.fifties ?:0).toString(),(odi?.fifties ?:0).toString(),(league?.fifties ?:0).toString()))
                keyValueList.add(PlayerGridItem("100s", (test?.hundreds ?:0).toString(),(t20?.hundreds ?:0).toString(),(odi?.hundreds ?:0).toString(),(league?.hundreds ?:0).toString()))
            }

            //Grid Adapter call
            gridView.adapter = PlayerDetailsGridAdapter(requireContext(), keyValueList)
        }
    }
}