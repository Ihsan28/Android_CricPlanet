package com.ihsan.cricplanet.ui.fragment.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.viewpager.TabPlayerAdapter
import com.ihsan.cricplanet.databinding.FragmentPlayerDetailsTabLayoutBinding
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel

class PlayerDetailsTabLayoutFragment : Fragment() {
    private lateinit var binding:FragmentPlayerDetailsTabLayoutBinding
    private val viewmodel: CricViewModel by viewModels()
    private var playerId=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentPlayerDetailsTabLayoutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout=binding.tabLayoutPlayerDetails
        val viewPager=binding.viewPager2PlayerDetails
        arguments.let {
            if (it != null) {
                playerId=it.getInt("playerId")
            }
            viewmodel.getPlayersByIdApi(playerId)
            viewmodel.playerDetails.observe(viewLifecycleOwner){player->
                val tabDetailPlayerAdapter=TabPlayerAdapter(childFragmentManager,lifecycle,player)
                viewPager.adapter=tabDetailPlayerAdapter
                TabLayoutMediator(tabLayout,viewPager){tab,position->
                    tab.text=TabPlayerAdapter.listPlayerDetailTab[position].category
                }.attach()

                binding.playerName.text=player.fullname
                binding.playerCountry.text=player.country?.name ?:""
                Utils().also { utils-> utils.loadImageWithPicasso(player.image_path,binding.playerImage) }

            }

        }

    }
}