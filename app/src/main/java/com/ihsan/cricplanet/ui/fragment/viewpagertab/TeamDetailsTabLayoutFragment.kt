package com.ihsan.cricplanet.ui.fragment.viewpagertab

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.ihsan.cricplanet.adapter.viewpager.TabTeamDetailAdapter
import com.ihsan.cricplanet.databinding.FragmentTeamDetailsTabLayoutBinding
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel

class TeamDetailsTabLayoutFragment : Fragment() {
    private lateinit var binding: FragmentTeamDetailsTabLayoutBinding
    private val viewmodel: CricViewModel by viewModels()
    private var teamId: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTeamDetailsTabLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar=Utils().progressAnimationStart(requireContext(),"Loading Team...")

        //Tab layout
        val tabLayout = binding.tabLayoutTeamDetails
        val viewPager = binding.viewPager2TeamDetails

        arguments?.let {

            //getting object from parameter
            teamId = it.getInt("teamId")
            Log.d("cricDetailsTabLayout", "onViewCreated: $teamId")

            //calling api
            viewmodel.getTeamByIdApi(teamId)
            //Assigning match Adapter
            viewmodel.teamById.observe(viewLifecycleOwner) { team ->

                val tabMatchDetailAdapter =
                    TabTeamDetailAdapter(childFragmentManager, lifecycle, team)
                viewPager.adapter = tabMatchDetailAdapter
                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.text = tabMatchDetailAdapter.listTeamDetailTab[position].category
                }.attach()

                //stop progress Animation
                Utils().progressAnimationStop(progressBar)

                //Assigning value of all view fields of top
                binding.teamName.text = team.name
                Utils().loadImageWithPicasso(team.image_path, binding.teamImage)

                if (team.national_team == true) {
                    binding.teamType.text = "National"
                } else {
                    binding.teamType.text = "League"
                    binding.teamCountry.text = team.country?.name ?: ""
                }

            }
        }

        //Auto Hide Top view
        var mBottomViewVisible = true
        val scrollView = binding.detailsTeamScrollView
        val mBottomView = binding.topInfo
        scrollView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY && mBottomViewVisible) {
                mBottomView.animate()?.translationY(mBottomView.height.toFloat() ?: 0f)?.start()
                mBottomViewVisible = true
            } else if (scrollY < oldScrollY && !mBottomViewVisible) {
                mBottomView.animate()?.translationY(0f)?.start()
                mBottomViewVisible = false
            }
        }
    }

}