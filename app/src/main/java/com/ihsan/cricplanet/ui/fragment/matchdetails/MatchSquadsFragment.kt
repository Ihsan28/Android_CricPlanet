package com.ihsan.cricplanet.ui.fragment.matchdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.databinding.FragmentMatchInfoBinding
import com.ihsan.cricplanet.databinding.FragmentMatchSquadsBinding
import com.ihsan.cricplanet.model.GridItem

class MatchSquadsFragment : Fragment() {
    private lateinit var binding: FragmentMatchSquadsBinding
    var keyValueList= mutableListOf<GridItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMatchSquadsBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }
}