package com.ihsan.cricplanet.ui.fragment.teamdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ihsan.cricplanet.adapter.grid.TeamSquadAdapter
import com.ihsan.cricplanet.databinding.FragmentTeamSquadBinding
import com.ihsan.cricplanet.model.team.TeamDetails
import com.ihsan.cricplanet.ui.fragment.viewpagertab.TeamDetailsTabLayoutFragment.Companion.mBottomViewVisible
import com.ihsan.cricplanet.ui.fragment.viewpagertab.callBackInterface.DetailsTabLayoutFragmentCallback
import com.ihsan.cricplanet.utils.MyApplication
import com.ihsan.cricplanet.utils.Utils

@Suppress("DEPRECATION")
class TeamSquadFragment : Fragment() {
    private lateinit var binding: FragmentTeamSquadBinding
    var parentFragmentCallback: DetailsTabLayoutFragmentCallback? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTeamSquadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var team: TeamDetails?
        val gridViewTeam = binding.gridViewSquadTeam


        arguments.let {
            if (it != null) {
                team = it.getParcelable("team")

                if (team != null) {
                    //set adapter
                    gridViewTeam.adapter =
                        team!!.squad?.let { it1 -> TeamSquadAdapter(requireContext(), it1) }
                    // Add a footer view to create space after the last item
                    Utils().addSpaceAtBottomOfList(gridViewTeam)
                }
            }

            //Auto Hide Top view
            gridViewTeam.setOnScrollListener(object : AbsListView.OnScrollListener {
                private var lastFirstVisibleItem: Int = 0

                override fun onScroll(
                    view: AbsListView?,
                    firstVisibleItem: Int,
                    visibleItemCount: Int,
                    totalItemCount: Int
                ) {
                    if (firstVisibleItem > lastFirstVisibleItem && mBottomViewVisible) {
                        Toast.makeText(MyApplication.instance, "call hide", Toast.LENGTH_SHORT)
                            .show()
                        parentFragmentCallback?.hideTopView()
                        mBottomViewVisible = false
                    } else if (firstVisibleItem < 2 && !mBottomViewVisible) {
                        Toast.makeText(MyApplication.instance, "call show", Toast.LENGTH_SHORT)
                            .show()
                        parentFragmentCallback?.showTopView()
                        mBottomViewVisible = true
                    }
                    lastFirstVisibleItem = firstVisibleItem
                }

                override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                    // No specific action needed
                }
            })
        }

    }
}