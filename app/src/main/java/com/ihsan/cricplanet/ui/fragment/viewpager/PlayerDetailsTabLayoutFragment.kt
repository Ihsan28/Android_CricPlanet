package com.ihsan.cricplanet.ui.fragment.viewpager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.ihsan.cricplanet.adapter.viewpager.TabPlayerAdapter
import com.ihsan.cricplanet.databinding.FragmentPlayerDetailsTabLayoutBinding
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel

private const val TAG = "PlayerDetailsTabLayoutF"

class PlayerDetailsTabLayoutFragment : Fragment() {
    private lateinit var binding: FragmentPlayerDetailsTabLayoutBinding
    private val viewmodel: CricViewModel by viewModels()
    private val args: PlayerDetailsTabLayoutFragmentArgs by navArgs()
    private var playerId: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        Toast.makeText(requireContext(), "called", Toast.LENGTH_SHORT).show()
        // Inflate the layout for this fragment
        binding = FragmentPlayerDetailsTabLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout = binding.tabLayoutPlayerDetails
        val viewPager = binding.viewPager2PlayerDetails
        arguments.let {
            if (it != null) {
                playerId = it.getInt("playerId")
            }

            viewmodel.getPlayersByIdApi(playerId)
            viewmodel.playerDetails.observe(viewLifecycleOwner) { player ->
                val tabPlayerAdapter = TabPlayerAdapter(childFragmentManager, lifecycle, player)
                //Checking if player has no batting and bowling data
                if (player?.career?.map { it.bowling == null }?.contains(false) != true) {
                    //removing bowling tab
                    tabPlayerAdapter.listPlayerDetailTab.removeAt(2)
                    Log.d(TAG, "onViewCreated: ${tabPlayerAdapter.listPlayerDetailTab}")

                }
                if (player?.career?.map { it.batting == null }?.contains(false) != true) {
                    //removing batting tab
                    tabPlayerAdapter.listPlayerDetailTab.removeAt(1)
                }

                Log.d(TAG, "onViewCreated:Career ${player.career}")

                viewPager.adapter = tabPlayerAdapter
                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.text = tabPlayerAdapter.listPlayerDetailTab[position].category
                }.attach()

                binding.playerName.text = player.fullname
                binding.playerCountry.text = player.country?.name ?: ""
                binding.playerType.text = player.position?.name ?: ""
                Utils().also { utils ->
                    utils.loadImageWithPicasso(
                        player.image_path, binding.playerImage
                    )
                }
            }
        }
    }
}