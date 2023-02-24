package com.ihsan.cricplanet.ui.fragment.viewpager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.viewpager.TabMatchDetailAdapter
import com.ihsan.cricplanet.databinding.FragmentMatchDetailTabLayoutBinding
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel

class MatchDetailTabLayoutFragment : Fragment() {

    private lateinit var binding: FragmentMatchDetailTabLayoutBinding
    private val viewmodel: CricViewModel by viewModels()
    private var matchId: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMatchDetailTabLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Tab layout
        val tabLayout = binding.tabLayoutMatchDetails
        val viewPager = binding.viewPager2MatchDetails
        val fixtureName = view.findViewById<TextView>(R.id.fixture_name)
        val fixtureStatus = view.findViewById<TextView>(R.id.fixture_status)
        val localTeamName = view.findViewById<TextView>(R.id.local_team_name)
        val localTeamRun = view.findViewById<TextView>(R.id.local_team_run)
        val localTeamOver = view.findViewById<TextView>(R.id.local_team_over)
        val localTeamImage = view.findViewById<ImageView>(R.id.local_team_image)
        val visitorTeamName = view.findViewById<TextView>(R.id.visitor_team_name)
        val visitorTeamRun = view.findViewById<TextView>(R.id.visitor_team_run)
        val visitorTeamOver = view.findViewById<TextView>(R.id.visitor_team_over)
        val visitorTeamImage = view.findViewById<ImageView>(R.id.visitor_team_image)

        arguments?.let {

            matchId = it.getInt("matchId")
            viewmodel.getFixturesByIdApi(matchId)
            Log.d("cricDetailsTabLayout", "onViewCreated: $matchId")

            //Assigning match Adapter
            viewmodel.fixtureByIdWithDetails.observe(viewLifecycleOwner) { it1 ->
                val tabMatchDetailAdapter =
                    TabMatchDetailAdapter(childFragmentManager, lifecycle, it1)
                viewPager.adapter = tabMatchDetailAdapter
                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.text = TabMatchDetailAdapter.listMatchDetailTab[position].category
                }.attach()

                //Assigning value of all view fields of top
                fixtureName.text = it1.league?.name
                Utils().also { it2 ->
                    it2.setStatus(it1.status, fixtureStatus)
                    it2.setRunsWithTeamName(
                        it1.runs,
                        it1.localteam,
                        it1.visitorteam,
                        localTeamName,
                        localTeamImage,
                        localTeamRun,
                        localTeamOver,
                        visitorTeamName,
                        visitorTeamImage,
                        visitorTeamRun,
                        visitorTeamOver
                    )
                }

            }
        }

        //Auto Hide Top view
        var mBottomViewVisible = true
        val scrollView = binding.detailsMatchScrollView
        val mBottomView = binding.topInfo
        scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY && mBottomViewVisible) {
                mBottomView?.animate()?.translationY(mBottomView?.height?.toFloat() ?: 0f)?.start()
                mBottomViewVisible = true
            } else if (scrollY < oldScrollY && !mBottomViewVisible) {
                mBottomView?.animate()?.translationY(0f)?.start()
                mBottomViewVisible = false
            }
        }
    }
}