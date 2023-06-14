package com.ihsan.cricplanet.ui.fragment.playerdetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ihsan.cricplanet.adapter.grid.PlayerDetailsGridAdapter
import com.ihsan.cricplanet.databinding.FragmentPlayerBowlingBinding
import com.ihsan.cricplanet.model.PlayerGridItem
import com.ihsan.cricplanet.model.player.PlayerDetails
import com.ihsan.cricplanet.utils.Utils

@Suppress("DEPRECATION")
class PlayerBowlingFragment : Fragment() {
    private lateinit var binding: FragmentPlayerBowlingBinding
    private var keyValueList = mutableListOf<PlayerGridItem>()
    private val args: PlayerBowlingFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayerBowlingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val player: PlayerDetails?
        val listView = binding.playerBowlingListView
        arguments.let {
            if (it != null) {
                player=it.getParcelable("player")
                Log.d("cricPlayerInfo", "onViewCreated: ${player?.id}")
                val test= player?.career?.get(0)?.bowling
                val t20= player?.career?.get(1)?.bowling
                val odi= player?.career?.get(2)?.bowling
                val league= player?.career?.get(3)?.bowling
                val ut=Utils()
                keyValueList.add(PlayerGridItem("Matches", (test?.matches ?:0).toString(),(t20?.matches ?:0).toString(),(odi?.matches ?:0).toString(),(league?.matches ?:0).toString() ))
                keyValueList.add(PlayerGridItem("Innings", (test?.innings ?:0).toString(),(t20?.innings ?:0).toString(),(odi?.innings ?:0).toString(),(league?.innings ?:0).toString()))
                keyValueList.add(PlayerGridItem("Runs", (test?.runs ?:0).toString(),(t20?.runs ?:0).toString(),(odi?.runs ?:0).toString(),(league?.runs ?:0).toString()))
                keyValueList.add(PlayerGridItem("Overs", (test?.overs ?:0).toString(),(t20?.overs ?:0).toString(),(odi?.overs ?:0).toString(),(league?.overs ?:0).toString()))
                keyValueList.add(PlayerGridItem("Eco", ut.twoDecimal(test?.econ_rate),ut.twoDecimal(t20?.econ_rate),ut.twoDecimal(odi?.econ_rate),ut.twoDecimal(league?.econ_rate)))
                keyValueList.add(PlayerGridItem("SR", ut.twoDecimal(test?.strike_rate),ut.twoDecimal(t20?.strike_rate),ut.twoDecimal(odi?.strike_rate),ut.twoDecimal(league?.strike_rate)))
                keyValueList.add(PlayerGridItem("Rate", ut.twoDecimal(test?.rate),ut.twoDecimal(t20?.rate),ut.twoDecimal(odi?.rate),ut.twoDecimal(league?.rate)))
                keyValueList.add(PlayerGridItem("Maidens", (test?.medians ?:0).toString(),(t20?.medians ?:0).toString(),(odi?.medians ?:0).toString(),(league?.medians ?:0).toString()))
                keyValueList.add(PlayerGridItem("Average", ut.twoDecimal(test?.average),ut.twoDecimal(t20?.average),ut.twoDecimal(odi?.average),ut.twoDecimal(league?.average)))
                keyValueList.add(PlayerGridItem("Wickets", (test?.wickets ?:0).toString(),(t20?.wickets ?:0).toString(),(odi?.wickets ?:0).toString(),(league?.wickets ?:0).toString()))
                keyValueList.add(PlayerGridItem("Wide", (test?.wide ?:0).toString(),(t20?.wide ?:0).toString(),(odi?.wide ?:0).toString(),(league?.wide ?:0).toString()))
                keyValueList.add(PlayerGridItem("No Ball", (test?.noball ?:0).toString(),(t20?.noball ?:0).toString(),(odi?.noball ?:0).toString(),(league?.noball ?:0).toString()))
                keyValueList.add(PlayerGridItem("4w", (test?.four_wickets ?:0).toString(),(t20?.four_wickets ?:0).toString(),(odi?.four_wickets ?:0).toString(),(league?.four_wickets ?:0).toString()))
                keyValueList.add(PlayerGridItem("5w", (test?.five_wickets ?:0).toString(),(t20?.five_wickets ?:0).toString(),(odi?.five_wickets ?:0).toString(),(league?.five_wickets ?:0).toString()))
                keyValueList.add(PlayerGridItem("10w", (test?.ten_wickets ?:0).toString(),(t20?.ten_wickets ?:0).toString(),(odi?.ten_wickets ?:0).toString(),(league?.ten_wickets ?:0).toString()))
            }

            //Grid Adapter call
            listView.adapter = PlayerDetailsGridAdapter(requireContext(), keyValueList)
        }
    }
}