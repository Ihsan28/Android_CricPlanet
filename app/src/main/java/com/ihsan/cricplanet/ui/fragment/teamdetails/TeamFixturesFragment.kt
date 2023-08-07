package com.ihsan.cricplanet.ui.fragment.teamdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.cricplanet.adapter.TeamMatchAdapter
import com.ihsan.cricplanet.databinding.FragmentTeamFixturesBinding
import com.ihsan.cricplanet.model.team.TeamDetails
import com.ihsan.cricplanet.ui.fragment.teamdetails.viewpager.TeamDetailsTabLayoutFragment.Companion.mBottomViewVisible
import com.ihsan.cricplanet.ui.fragment.viewpagertab.callBackInterface.DetailsTabLayoutFragmentCallback
import com.ihsan.cricplanet.utils.BottomSpaceItemDecoration
import com.ihsan.cricplanet.utils.MyApplication

@Suppress("DEPRECATION")
class TeamFixturesFragment : Fragment() {
    private lateinit var binding: FragmentTeamFixturesBinding
    private lateinit var recyclerView: RecyclerView
    var parentFragmentCallback: DetailsTabLayoutFragmentCallback? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTeamFixturesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initializing recycler view
        recyclerView = binding.recyclerviewMatches
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)

        var team: TeamDetails
        // getting the argument from navigation argument
        arguments.let {
            if (it != null) {
                team = it.getParcelable("team")!!
                recyclerView.adapter = team.results?.let { it1 -> TeamMatchAdapter(it1) }
                // Adding bottom space decoration
                recyclerView.addItemDecoration(BottomSpaceItemDecoration())
            }
            //Auto Hide Top view
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                private var lastFirstVisibleItem: Int = 0

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val firstVisibleItem =
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                    if (firstVisibleItem > lastFirstVisibleItem && mBottomViewVisible) {
                        Toast.makeText(MyApplication.instance, "call hide", Toast.LENGTH_SHORT)
                            .show()
                        parentFragmentCallback?.hideTopView()
                        mBottomViewVisible = false
                    } else if (firstVisibleItem < 1 && !mBottomViewVisible) {
                        Toast.makeText(MyApplication.instance, "call show", Toast.LENGTH_SHORT)
                            .show()
                        parentFragmentCallback?.showTopView()
                        mBottomViewVisible = true
                    }
                    lastFirstVisibleItem = firstVisibleItem
                }
            })
        }
    }
}