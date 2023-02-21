package com.ihsan.cricplanet.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.fixture.FixtureIncludeForCard
import com.ihsan.cricplanet.utils.MyApplication
import com.ihsan.cricplanet.utils.Utils
import com.squareup.picasso.Picasso

class MatchAdapterHome(private val matchList: List<FixtureIncludeForCard>) :
    RecyclerView.Adapter<MatchAdapterHome.MatchViewHolder>() {
    class MatchViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        val matchName: TextView = itemView.findViewById(R.id.fixture_name)
        val matchRound: TextView = itemView.findViewById(R.id.match_round)
        val localTeamName: TextView = binding.findViewById(R.id.local_team_name)
        val visitorTeamName: TextView = binding.findViewById(R.id.visitor_team_name)
        val localTeamImage: ImageView = itemView.findViewById(R.id.local_team_image)
        val visitorTeamImage: ImageView = itemView.findViewById(R.id.visitor_team_image)
        val status: TextView = itemView.findViewById(R.id.fixture_status)
        val noteOrVenue: TextView = itemView.findViewById(R.id.fixture_note_venue)
        val upcomingDate: TextView = itemView.findViewById(R.id.fixture_date)
        val upcomingTime: TextView = itemView.findViewById(R.id.fixture_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.home_match_card_item, parent, false)
        Log.d("teamAdapter", "onCreateViewHolder: ${matchList.size}")
        return MatchViewHolder(root)
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged", "ResourceAsColor")
    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matchList[position]
        val dateTimeList = Utils().dateFormat(match.starting_at!!)
        Log.d("teamAdapter", "BindViewHolder: ${matchList.size}")
        holder.matchName.text = "${match.league?.name} • ${match.type}"
        holder.matchRound.text=match.round
        holder.localTeamName.text = match.localteam!!.name
        holder.visitorTeamName.text = match.visitorteam!!.name
        holder.localTeamImage.setImageResource(R.drawable.ic_image)
        holder.visitorTeamImage.setImageResource(R.drawable.ic_image)
        holder.upcomingDate.text = dateTimeList[0]
        holder.upcomingTime.text = dateTimeList[1]

        if (!TextUtils.isEmpty(match.localteam.image_path)) {
            Picasso.get().load(match.localteam.image_path).fit()
                .placeholder(R.drawable.progress_animation).into(holder.localTeamImage)
        } else {
            holder.localTeamImage.setImageResource(R.drawable.ic_image)
        }
        if (!TextUtils.isEmpty(match.visitorteam.image_path)) {
            Picasso.get().load(match.visitorteam.image_path).fit()
                .placeholder(R.drawable.progress_animation).into(holder.visitorTeamImage)
        } else {
            holder.localTeamImage.setImageResource(R.drawable.ic_image)
        }

        if (match.status == "Finished") {
            holder.status.setBackgroundColor(
                ContextCompat.getColor(
                    MyApplication.instance, R.color.md_blue_grey_700
                )
            )
            holder.status.text = match.status
            holder.noteOrVenue.text = match.note
        } else {
            if (match.status == "NS") {
                holder.status.text = "UPCOMING"
                holder.status.setBackgroundColor(
                    ContextCompat.getColor(
                        MyApplication.instance, R.color.md_yellow_700
                    )
                )
            } else {

                if (match.status == "Postp.") {
                    holder.status.text = "POSTPONED"
                    holder.status.setBackgroundColor(
                        ContextCompat.getColor(
                            MyApplication.instance, R.color.md_yellow_900
                        )
                    )
                } else if (match.status == "Aban.") {
                    holder.status.text = "ABANDONED"
                    holder.status.setBackgroundColor(
                        ContextCompat.getColor(
                            MyApplication.instance, R.color.md_red_200
                        )
                    )
                } else if (match.live == true) {
                    Log.d("cricMatchAdapter", "onBindViewHolderLive: $match")
                    holder.status.text = "• LIVE"
                    holder.status.setBackgroundColor(
                        ContextCompat.getColor(
                            MyApplication.instance, R.color.md_red_400
                        )
                    )
                }
            }

            if (match.venue?.name == null || match.venue.city == null) {
                "Not Decided Yet".also { holder.noteOrVenue.text = it }
            } else {
                if (match.venue.country?.name != null) {
                    holder.noteOrVenue.text =
                        "${match.venue.name} • ${match.venue.city} • ${match.venue.country?.name}"
                }else{
                    holder.noteOrVenue.text = "${match.venue.name} • ${match.venue.city}"
                }
            }
        }

        holder.itemView.setOnClickListener{
            /*val action= match.let {
                MatchTabLayoutFragmentDirections.actionMatchTabLayoutFragmentToMatchDetailTabLayoutFragment(it.id)
            }
            holder.run { itemView.findNavController().navigate(action) }*/
            Log.d("cricMatchAdapter", "onBindViewHolder: ${match.id}")
            //crash issue
            Navigation.findNavController(holder.itemView).navigate(
                R.id.action_homeFragment_to_matchDetailTabLayoutFragment,
                Bundle().apply { putInt("matchId", match.id)})
        }
    }
}