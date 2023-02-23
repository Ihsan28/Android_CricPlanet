package com.ihsan.cricplanet.ui.fragment.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.databinding.FragmentPlayerDetailsTabLayoutBinding
import com.ihsan.cricplanet.viewmodel.CricViewModel

class PlayerDetailsTabLayoutFragment : Fragment() {
    private lateinit var binding:FragmentPlayerDetailsTabLayoutBinding
    private val viewmodel: CricViewModel by viewModels()

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


    }
}