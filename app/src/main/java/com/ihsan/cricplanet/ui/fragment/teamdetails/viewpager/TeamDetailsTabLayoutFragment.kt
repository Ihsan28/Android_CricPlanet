package com.ihsan.cricplanet.ui.fragment.teamdetails.viewpager

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.ihsan.cricplanet.adapter.viewpager.TabTeamDetailAdapter
import com.ihsan.cricplanet.databinding.FragmentTeamDetailsTabLayoutBinding
import com.ihsan.cricplanet.model.team.TeamDetails
import com.ihsan.cricplanet.ui.fragment.teamdetails.TeamFixturesFragment
import com.ihsan.cricplanet.ui.fragment.teamdetails.TeamSquadFragment
import com.ihsan.cricplanet.ui.fragment.viewpagertab.callBackInterface.DetailsTabLayoutFragmentCallback
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel

class TeamDetailsTabLayoutFragment : Fragment(), DetailsTabLayoutFragmentCallback {
    companion object {
        var mBottomViewVisible = true
    }

    private lateinit var bindingTeamDetailsTab: FragmentTeamDetailsTabLayoutBinding
    private val viewmodel: CricViewModel by viewModels()
    private var team: TeamDetails? = null
    private var teamId: Int = 0

    private val childFragmentLifecycleCallbacks =
        object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentAttached(
                fm: FragmentManager,
                childFragment: Fragment,
                context: Context
            ) {
                if (childFragment is TeamSquadFragment) {
                    childFragment.parentFragmentCallback = this@TeamDetailsTabLayoutFragment
                } else if (childFragment is TeamFixturesFragment) {
                    childFragment.parentFragmentCallback = this@TeamDetailsTabLayoutFragment
                }
            }
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.registerFragmentLifecycleCallbacks(
            childFragmentLifecycleCallbacks,
            false
        )
    }

    override fun onDetach() {
        super.onDetach()
        childFragmentManager.unregisterFragmentLifecycleCallbacks(childFragmentLifecycleCallbacks)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingTeamDetailsTab =
            FragmentTeamDetailsTabLayoutBinding.inflate(inflater, container, false)
        return bindingTeamDetailsTab.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = Utils().progressAnimationStart(requireContext(), "Loading Team...")



        arguments?.let {

            //getting object from parameter
            teamId = it.getInt("teamId")
            Log.d("cricDetailsTabLayout", "onViewCreated: $teamId")

            if (teamId != 0 && team == null) {
                //calling api
                viewmodel.getTeamByIdApi(teamId)
                //Assigning match Adapter
                viewmodel.teamById.observe(viewLifecycleOwner) { teamDetails ->
                    team = teamDetails
                    loadTeamDetailsToView(view)
                    Utils().progressAnimationStop(progressBar)
                }
            }else{
                loadTeamDetailsToView(view)
                Utils().progressAnimationStop(progressBar)
            }
        }
    }

    private fun loadTeamDetailsToView(view: View){
        //Tab layout
        val tabLayout = bindingTeamDetailsTab.tabLayoutTeamDetails
        val viewPager = bindingTeamDetailsTab.viewPager2TeamDetails

        val tabMatchDetailAdapter =
            TabTeamDetailAdapter(childFragmentManager, lifecycle, team!!)
        viewPager.adapter = tabMatchDetailAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabMatchDetailAdapter.listTeamDetailTab[position].category
        }.attach()

        //Assigning value of all view fields of top
        bindingTeamDetailsTab.teamName.text = team!!.name
        Utils().loadImageWithPicasso(team!!.image_path, bindingTeamDetailsTab.teamImage)

        if (team!!.national_team == true) {
            bindingTeamDetailsTab.teamType.text = "National"
        } else {
            bindingTeamDetailsTab.teamType.text = "League"
            bindingTeamDetailsTab.teamCountry.text = team!!.country?.name ?: ""
        }
    }

    override fun hideTopView() {
        Utils().animateHideTopView(
            bindingTeamDetailsTab.detailsTeamContainer,
            bindingTeamDetailsTab.topInfo,
            bindingTeamDetailsTab.tabLayoutTeamDetails,
            bindingTeamDetailsTab.viewPager2TeamDetails)
    }

    override fun showTopView() {
        Utils().animateShowTopView(
            bindingTeamDetailsTab.detailsTeamContainer,
            bindingTeamDetailsTab.topInfo,
            bindingTeamDetailsTab.tabLayoutTeamDetails,
            bindingTeamDetailsTab.viewPager2TeamDetails)
    }
}