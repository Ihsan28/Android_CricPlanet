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
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.fixture.FixtureIncludeForCard
import com.ihsan.cricplanet.utils.Utils
import com.squareup.picasso.Picasso

class MatchAdapter(private val matchList: List<FixtureIncludeForCard>) :
    RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {
    //private val viewModel: CricViewModel = CricViewModel(application = Application())
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
        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.match_card_item, parent, false)
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
        holder.matchRound.text = match.round
        holder.localTeamName.text = match.localteam!!.name
        holder.visitorTeamName.text = match.visitorteam!!.name
        holder.localTeamImage.setImageResource(R.drawable.ic_image)
        holder.visitorTeamImage.setImageResource(R.drawable.ic_image)
        holder.upcomingDate.text = dateTimeList[0]
        holder.upcomingTime.text = dateTimeList[1]

        Utils().also {
            //setting image path for team in card
            it.loadImageWithPicasso(match.localteam.image_path, holder.localTeamImage)
            it.loadImageWithPicasso(match.visitorteam.image_path, holder.visitorTeamImage)
            //set status and background color
            it.setStatus(match.status, holder.status)
            //set Venue or Note of the match
            it.setVenue(match.status, match.note, match.venue, holder.noteOrVenue)
        }

        holder.itemView.setOnClickListener {
            Log.d("cricMatchAdapter", "onBindViewHolder: ${match.id}")
            //crash issue
            Navigation.findNavController(holder.itemView)
                .navigate(R.id.action_matchTabLayoutFragment_to_matchDetailTabLayoutFragment,
                    Bundle().apply { putInt("matchId", match.id) })
        }
    }
}