package com.ihsan.cricplanet.ui.fragment.viewpager

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.viewpager.TabMatchDetailAdapter
import com.ihsan.cricplanet.databinding.FragmentMatchDetailTabLayoutBinding
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel

@Suppress("DEPRECATION")
class MatchDetailTabLayoutFragment : Fragment() {

    private lateinit var binding: FragmentMatchDetailTabLayoutBinding
    private val viewmodel: CricViewModel by viewModels()
    private var matchId: Int = 0
    private val liveHandler = Handler(Looper.getMainLooper())
    private var runnable:Runnable?=null
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
            viewmodel.fixtureByIdWithDetails.observe(viewLifecycleOwner) { match ->
                val tabMatchDetailAdapter = TabMatchDetailAdapter(childFragmentManager, lifecycle, match)
                viewPager.adapter = tabMatchDetailAdapter
                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.text = TabMatchDetailAdapter.listMatchDetailTab[position].category
                }.attach()

                //Assigning value of all view fields of top
                fixtureName.text = match.league?.name
                Utils().also { utils ->
                    //Automatic refresh page function call
                    if (utils.isLive(match.status.toString())){
                        /*stopPeriodicRefresh()
                        runnable = object : Runnable {
                            override fun run() {
                                refreshPage()
                                // 1 minute interval
                                liveHandler.postDelayed(this, 60000)
                            }
                        }
                        startPeriodicRefresh()*/
                    }
                    //setting status and runs
                    utils.setStatus(match.status, fixtureStatus)
                    utils.setRunsWithTeamName(
                        match.runs,
                        match.localteam,
                        match.visitorteam,
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
        scrollView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY && mBottomViewVisible) {
                mBottomView?.animate()?.translationY(mBottomView?.height?.toFloat() ?: 0f)?.start()
                mBottomViewVisible = true
            } else if (scrollY < oldScrollY && !mBottomViewVisible) {
                mBottomView?.animate()?.translationY(0f)?.start()
                mBottomViewVisible = false
            }
        }
    }
    private fun refreshPage() {
        Toast.makeText(requireActivity(), "refresh", Toast.LENGTH_SHORT).show()
        viewmodel.getFixturesByIdApi(matchId)
    }

    fun startPeriodicRefresh() {
        // 1 minute interval
        runnable?.let { liveHandler.postDelayed(it, 6000) }
    }

    fun stopPeriodicRefresh() {
        runnable?.let { liveHandler.removeCallbacks(it) }
    }

    override fun onResume() {
        super.onResume()
        //startPeriodicRefresh()
    }
    override fun onPause() {
        super.onPause()
        //stopPeriodicRefresh()
    }
}