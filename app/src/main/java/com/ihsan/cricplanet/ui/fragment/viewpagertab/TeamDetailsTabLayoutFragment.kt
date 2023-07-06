package com.ihsan.cricplanet.ui.fragment.viewpagertab

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.viewpager.TabTeamDetailAdapter
import com.ihsan.cricplanet.databinding.FragmentTeamDetailsTabLayoutBinding
import com.ihsan.cricplanet.ui.fragment.callBackInterface.TeamDetailsTabLayoutFragmentCallback
import com.ihsan.cricplanet.ui.fragment.teamdetails.TeamSquadFragment
import com.ihsan.cricplanet.utils.MyApplication
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel

class TeamDetailsTabLayoutFragment : Fragment(), TeamDetailsTabLayoutFragmentCallback {
    private lateinit var bindingTeamDetailsTab: FragmentTeamDetailsTabLayoutBinding
    private val viewmodel: CricViewModel by viewModels()
    private var teamId: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingTeamDetailsTab = FragmentTeamDetailsTabLayoutBinding.inflate(inflater, container, false)
        return bindingTeamDetailsTab.root
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        if (childFragment is TeamSquadFragment) {
            childFragment.parentFragmentCallback = this
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //childFragmentManager.addFragmentOnAttachListener(context)
    }
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar=Utils().progressAnimationStart(requireContext(),"Loading Team...")

        //Tab layout
        val tabLayout = bindingTeamDetailsTab.tabLayoutTeamDetails
        val viewPager = bindingTeamDetailsTab.viewPager2TeamDetails

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

                Thread.sleep(1000)

                setupChildFragments()

                //stop progress Animation
                Utils().progressAnimationStop(progressBar)

                //Assigning value of all view fields of top
                bindingTeamDetailsTab.teamName.text = team.name
                Utils().loadImageWithPicasso(team.image_path, bindingTeamDetailsTab.teamImage)

                if (team.national_team == true) {
                    bindingTeamDetailsTab.teamType.text = "National"
                } else {
                    bindingTeamDetailsTab.teamType.text = "League"
                    bindingTeamDetailsTab.teamCountry.text = team.country?.name ?: ""
                }

            }
        }

        //Auto Hide Top view
        var mBottomViewVisible = true
        val scrollView = bindingTeamDetailsTab.detailsTeamContainer
        val mBottomView = bindingTeamDetailsTab.topInfo
        scrollView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY && mBottomViewVisible) {
                mBottomView.animate()?.translationY(mBottomView.height.toFloat() ?: 0f)?.start()
                mBottomViewVisible = false
            } else if (scrollY < oldScrollY && !mBottomViewVisible) {
                mBottomView.animate()?.translationY(0f)?.start()
                mBottomViewVisible = true
            }
        }
    }

    private fun setupChildFragments() {
        val childFragment1 = TeamSquadFragment()

        childFragment1.parentFragmentCallback = this
        Toast.makeText(MyApplication.instance, "assigned", Toast.LENGTH_SHORT).show()
        // Attach the child fragments to the tab layout or any other container
        // ...
    }

    override fun hideTopView(){
        Toast.makeText(MyApplication.instance, "hide", Toast.LENGTH_SHORT).show()
        val mBottomView = bindingTeamDetailsTab.topInfo
        mBottomView.animate()?.translationY(mBottomView.height.toFloat())?.start()
    }

    override fun showTopView(){
        Toast.makeText(MyApplication.instance, "show", Toast.LENGTH_SHORT).show()
        val mBottomView = bindingTeamDetailsTab.topInfo
        mBottomView.animate()?.translationY(0f)?.start()
    }
}