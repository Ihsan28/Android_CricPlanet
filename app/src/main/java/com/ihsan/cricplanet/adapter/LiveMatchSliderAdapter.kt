package com.ihsan.cricplanet.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.fixture.FixtureIncludeForCard
import com.ihsan.cricplanet.utils.MyApplication
import com.ihsan.cricplanet.utils.Utils
import com.squareup.picasso.Picasso

class LiveMatchSliderAdapter(
    private val context: Context,
    private var liveMatchList: List<FixtureIncludeForCard>
) : PagerAdapter() {
    override fun getCount(): Int {
        return liveMatchList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    @SuppressLint("SetTextI18n")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View =
            (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.match_card_item, null
            )
        val matchName: TextView = itemView.findViewById(R.id.fixture_name)
        val matchRound: TextView = itemView.findViewById(R.id.match_round)
        val localTeamName: TextView = itemView.findViewById(R.id.local_team_name)
        val visitorTeamName: TextView = itemView.findViewById(R.id.visitor_team_name)
        val localTeamImage: ImageView = itemView.findViewById(R.id.local_team_image)
        val visitorTeamImage: ImageView = itemView.findViewById(R.id.visitor_team_image)
        val status: TextView = itemView.findViewById(R.id.fixture_status)
        val noteOrVenue: TextView = itemView.findViewById(R.id.fixture_note_venue)
        //val upcomingDate: TextView = itemView.findViewById(R.id.fixture_date)
        val upcomingTime: TextView = itemView.findViewById(R.id.fixture_time)

        liveMatchList[position].let {

            val dateTimeList = Utils().dateFormat(it.starting_at!!)
            Log.d("teamAdapter", "BindViewHolder: ${liveMatchList.size}")
            matchName.text = "${it.league?.name} • ${it.type}"
            matchRound.text = it.round
            localTeamName.text = it.localteam!!.name
            visitorTeamName.text = it.visitorteam!!.name
            localTeamImage.setImageResource(R.drawable.ic_image)
            visitorTeamImage.setImageResource(R.drawable.ic_image)
            //upcomingDate.text = dateTimeList[0]
            upcomingTime.text = "Started at ${dateTimeList[1]}"

            if (!TextUtils.isEmpty(it.localteam.image_path)) {
                Picasso.get().load(it.localteam.image_path).fit()
                    .placeholder(R.drawable.progress_animation).into(localTeamImage)
            } else {
                localTeamImage.setImageResource(R.drawable.ic_image)
            }
            if (!TextUtils.isEmpty(it.visitorteam.image_path)) {
                Picasso.get().load(it.visitorteam.image_path).fit()
                    .placeholder(R.drawable.progress_animation).into(visitorTeamImage)
            } else {
                localTeamImage.setImageResource(R.drawable.ic_image)
            }

            Log.d("cricSlider", "instantiateItem: $it")
            if (it.live == true) {
                Log.d("cricMatchAdapter", "onBindViewHolderLive: $it")
                status.text = "• LIVE"
                status.setBackgroundColor(
                    ContextCompat.getColor(
                        MyApplication.instance, R.color.md_red_400
                    )
                )
            }

            if (it.venue?.name == null || it.venue.city == null) {
                "Not Decided Yet".also { noteOrVenue.text = it }
            } else {
                noteOrVenue.text = "${it.venue.name} • ${it.venue.city}"
            }
        }

        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}