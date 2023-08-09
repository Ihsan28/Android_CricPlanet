package com.ihsan.cricplanet.ui.fragment.matchdetails.viewpager

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayoutMediator
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.viewpager.TabMatchDetailAdapter
import com.ihsan.cricplanet.databinding.FragmentMatchDetailTabLayoutBinding
import com.ihsan.cricplanet.model.fixture.FixtureByIdWithDetails
import com.ihsan.cricplanet.ui.fragment.teamdetails.TeamFixturesFragment
import com.ihsan.cricplanet.ui.fragment.teamdetails.TeamSquadFragment
import com.ihsan.cricplanet.ui.fragment.viewpagertab.callBackInterface.DetailsTabLayoutFragmentCallback
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel

private const val TAG = "MatchDetailTabLayoutFragment"
class MatchDetailTabLayoutFragment : Fragment(), DetailsTabLayoutFragmentCallback {
    companion object {
        var mBottomViewVisible = true
    }

    private lateinit var binding: FragmentMatchDetailTabLayoutBinding
    private val viewmodel: CricViewModel by viewModels()
    private var match: FixtureByIdWithDetails? = null
    private var matchId: Int = 0
    private val liveHandler = Handler(Looper.getMainLooper())
    private var runnable: Runnable? = null
    private val DELAY_MS: Long = 60000 //refresh delay in millis

    private val childFragmentLifecycleCallbacks =
        object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentAttached(
                fm: FragmentManager,
                childFragment: Fragment,
                context: Context
            ) {
                if (childFragment is TeamSquadFragment) {
                    childFragment.parentFragmentCallback = this@MatchDetailTabLayoutFragment
                } else if (childFragment is TeamFixturesFragment) {
                    childFragment.parentFragmentCallback = this@MatchDetailTabLayoutFragment
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
        binding = FragmentMatchDetailTabLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = Utils().progressAnimationStart(requireContext(), "Loading Match...")

        arguments?.let {
            matchId = it.getInt("matchId")
        }

        if (matchId != 0 && match == null) {
            //Api call for match details
            viewmodel.getFixturesByIdApi(matchId)
            Log.d("cricDetailsTabLayout", "onViewCreated: $matchId")

            //Assigning match Adapter
            viewmodel.fixtureByIdWithDetails.observe(viewLifecycleOwner) { fixtureDetails ->
                match = fixtureDetails
                Log.d(TAG, "onViewCreated: $match")
                loadFixtureDetailsDatToView(view)
                //progress bar stop
                Utils().progressAnimationStop(progressBar)
            }
        } else {
            loadFixtureDetailsDatToView(view)
            //progress bar stop
            Utils().progressAnimationStop(progressBar)
        }
    }

    private fun loadFixtureDetailsDatToView(view: View){
        //Tab layout
        val tabLayout = binding.tabLayoutMatchDetails
        val viewPager = binding.viewPager2MatchDetails

        val fixtureName = view.findViewById<TextView>(R.id.fixture_name)
        val fixtureStatus = view.findViewById<TextView>(R.id.fixture_status)

        val localTeamName = view.findViewById<TextView>(R.id.local_team_name)
        val localTeamRun = view.findViewById<TextView>(R.id.local_team_run)
        val localTeamOver = view.findViewById<TextView>(R.id.local_team_over)
        val localTeamImage = view.findViewById<ImageView>(R.id.local_team_image)
        val localTeamImageCard = view.findViewById<CardView>(R.id.local_team_image_card)

        val visitorTeamName = view.findViewById<TextView>(R.id.visitor_team_name)
        val visitorTeamRun = view.findViewById<TextView>(R.id.visitor_team_run)
        val visitorTeamOver = view.findViewById<TextView>(R.id.visitor_team_over)
        val visitorTeamImage = view.findViewById<ImageView>(R.id.visitor_team_image)
        val visitorTeamImageCard = view.findViewById<CardView>(R.id.visitor_team_image_card)

        val tabMatchDetailAdapter =
            TabMatchDetailAdapter(childFragmentManager, lifecycle, match!!)

        //Removing tab if data is null
        if (match!!.batting?.isEmpty() != false && match!!.scoreboards?.isEmpty() != false) {
            tabMatchDetailAdapter.listMatchDetailTab.removeAt(2)
        }

        if (match!!.lineup?.isEmpty() != false) {
            tabMatchDetailAdapter.listMatchDetailTab.removeAt(1)
        }

        viewPager.adapter = tabMatchDetailAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabMatchDetailAdapter.listMatchDetailTab[position].category
        }.attach()

        //Assigning value of all view fields of top
        fixtureName.text = match!!.league?.name

        Utils().also { utils ->
            //Automatic refresh page function call
            if (utils.isLive(match!!.status.toString())) {
                stopPeriodicRefresh()
                runnable = object : Runnable {
                    override fun run() {
                        refreshPage()
                        // 1 minute interval
                        liveHandler.postDelayed(this, DELAY_MS)
                    }
                }
                startPeriodicRefresh()
            }
            //setting status and runs
            utils.setStatus(match!!.status, fixtureStatus)
            utils.setRunsWithTeamName(
                match!!.runs,
                match!!.localteam,
                match!!.visitorteam,
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

        localTeamImageCard.setOnClickListener {
            navigateToTeamDetails(match!!.localteam?.id!!)
        }
        visitorTeamImageCard.setOnClickListener {
            navigateToTeamDetails(match!!.visitorteam?.id!!)
        }
    }

    private fun navigateToTeamDetails(teamId: Int) {
        Navigation.findNavController(requireView())
            .navigate(
                R.id.action_matchDetailTabLayoutFragment_to_teamDetailsTabLayoutFragment,
                Bundle().apply { putInt("teamId", teamId) })
    }

    private fun refreshPage() {
        Utils().refreshMessage()
        viewmodel.getFixturesByIdApi(matchId)
    }

    private fun startPeriodicRefresh() {
        // 1 minute interval
        runnable?.let { liveHandler.postDelayed(it, DELAY_MS) }
    }

    private fun stopPeriodicRefresh() {
        runnable?.let { liveHandler.removeCallbacks(it) }
    }

    override fun onPause() {
        super.onPause()
        stopPeriodicRefresh()
    }

    override fun hideTopView() {
        Utils().animateHideTopView(
            binding.detailsTeamContainer,
            binding.topInfo,
            binding.tabLayoutMatchDetails,
            binding.viewPager2MatchDetails
        )
    }

    override fun showTopView() {
        Utils().animateShowTopView(
            binding.detailsTeamContainer,
            binding.topInfo,
            binding.tabLayoutMatchDetails,
            binding.viewPager2MatchDetails
        )
    }
}