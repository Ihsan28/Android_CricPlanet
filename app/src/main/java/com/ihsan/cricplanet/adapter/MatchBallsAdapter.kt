package com.ihsan.cricplanet.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.fixture.BallsIncludeBowler
import com.ihsan.cricplanet.utils.Utils

class MatchBallsAdapter(private val matchHistoryList: List<BallsIncludeBowler>) :
    RecyclerView.Adapter<MatchBallsAdapter.MatchViewHolder>() {

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
        Log.d("teamAdapter", "onCreateViewHolder: ${matchHistoryList.size}")
        return MatchViewHolder(root)
    }

    override fun getItemCount(): Int {
        return matchHistoryList.size
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged", "ResourceAsColor")
    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matchHistoryList[position]

        holder.matchName.text = "Scoreboard(${match.scoreboard}) • ${match.ball}"
        holder.matchRound.text = match.team?.name ?: ""
        holder.localTeamName.text = match.batsman?.fullname ?: ""
        holder.visitorTeamName.text = match.bowler?.fullname ?: ""
        holder.localTeamImage.setImageResource(R.drawable.ic_image)
        holder.visitorTeamImage.setImageResource(R.drawable.ic_image)
        holder.upcomingDate.text = "Six: ${match.score?.six}"
        holder.upcomingTime.text = "Six: ${match.score?.four}"

        Utils().also {
            //setting image path for team in card
            it.loadImageWithPicasso(match.batsman?.image_path, holder.localTeamImage)
            it.loadImageWithPicasso(match.bowler?.image_path, holder.visitorTeamImage)
            //set status and background color
            it.setStatus("Run: ${match.score?.name}", holder.status)
            //set Venue or Note of the match
            holder.noteOrVenue.text =
                "isWicket:${match.score?.is_wicket} • ${match.score?.runs} • ${match.score?.noball}"

        }
    }
}