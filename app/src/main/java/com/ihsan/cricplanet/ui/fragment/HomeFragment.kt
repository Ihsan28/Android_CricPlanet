package com.ihsan.cricplanet.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.*
import androidx.viewpager.widget.ViewPager
import com.ihsan.cricplanet.adapter.LiveMatchSliderAdapter
import com.ihsan.cricplanet.adapter.MatchAdapterHome
import com.ihsan.cricplanet.databinding.FragmentHomeBinding
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel
import kotlinx.coroutines.launch
import me.relex.circleindicator.CircleIndicator

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: CricViewModel by viewModels()
    private lateinit var recyclerViewToday: RecyclerView

    //Auto Slide Properties
    lateinit var viewPagerAdapter: LiveMatchSliderAdapter
    lateinit var indicator: CircleIndicator
    private val PERIOD_MS: Long = 4000
    private val sliderHandler = Handler()
    private var updateSlider: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        stopAutoSlide()
        startAutoSlide()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Live Card Slider view pager
        val viewPager = binding.viewpager
        val liveLayout = binding.liveSlider

        //Refresh Layout
        val refreshLayout=binding.swipeLayout

        //Live Api Call
        viewModel.getLiveFixturesApi()

        //Live Observer
        viewModel.liveFixture.observe(viewLifecycleOwner) {
            Log.d("cricHome", "onViewCreatedHomeSlider: $it")
            stopAutoSlide()
            viewPagerAdapter = LiveMatchSliderAdapter(requireContext(), it)
            viewPager.adapter = viewPagerAdapter
            if (it.size > 1) {
                autoSlide(viewPager)
            }
        }

        //Recycler view for Today
        recyclerViewToday = binding.recyclerviewToday
        recyclerViewToday.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewToday.setHasFixedSize(true)


        //Get Data From API for Today Fixture
        viewModel.getTodayFixturesApi()
        viewModel.todayFixture.observe(viewLifecycleOwner) {
            Log.d("cricTeam", "onViewCreated MatchFixture: $it")
            recyclerViewToday.adapter = MatchAdapterHome(it)
        }

        //Recycler view for Recent
        val recyclerViewRecent = binding.recyclerviewRecent
        recyclerViewRecent.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewRecent.setHasFixedSize(true)

        //Get Data From API
        viewModel.getRecentFixturesApi()
        viewModel.recentMatchFixture.observe(viewLifecycleOwner) {
            Log.d("cricTeam", "onViewCreated MatchFixture: $it")
            recyclerViewRecent.adapter = MatchAdapterHome(it)
        }

        //Refreshing The Home Page
        refreshLayout.setOnRefreshListener {
            viewModel.viewModelScope.launch {
                viewModel.getLiveFixturesApi()
                viewModel.getTodayFixturesApi()
                viewModel.getRecentFixturesApi()
                Utils().refreshMessage()
            }
            refreshLayout.isRefreshing = false
        }
    }

    override fun onPause() {
        super.onPause()
        stopAutoSlide()
    }

    private fun autoSlide(viewPager: ViewPager) {
        indicator = binding.indicator
        indicator.setViewPager(viewPager)
        updateSlider = Runnable {
            var currentPage = viewPager.currentItem
            val totalPages = viewPager.adapter?.count ?: 0
            currentPage = (currentPage + 1) % totalPages
            viewPager.currentItem = currentPage
            startAutoSlide()
        }
        startAutoSlide()
    }

    private fun startAutoSlide() {
        if (updateSlider != null) {
            sliderHandler.postDelayed(updateSlider!!, PERIOD_MS)
        }
    }

    private fun stopAutoSlide() {
        if (updateSlider != null) {
            sliderHandler.removeCallbacks(updateSlider!!)
        }
    }
}