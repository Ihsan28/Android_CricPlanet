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
import com.ihsan.cricplanet.model.fixture.Fixture
import com.ihsan.cricplanet.model.fixture.FixtureIncludeForCard
import com.ihsan.cricplanet.utils.Utils
import com.squareup.picasso.Picasso

class TeamMatchAdapter(private val matchList: List<Fixture>) :
    RecyclerView.Adapter<TeamMatchAdapter.MatchViewHolder>() {

    class MatchViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        val matchName: TextView = itemView.findViewById(R.id.fixture_name)
        val matchRound: TextView = itemView.findViewById(R.id.match_round)
        val status: TextView = itemView.findViewById(R.id.fixture_status)
        val noteOrVenue: TextView = itemView.findViewById(R.id.fixture_note_venue)
        val upcomingDate: TextView = itemView.findViewById(R.id.fixture_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.team_match_card_item, parent, false)
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
        holder.matchName.text = "${match.type}"
        holder.matchRound.text = match.round
        holder.upcomingDate.text = dateTimeList[0]
        holder.noteOrVenue.text =match.note
        Utils().also {
            //set status and background color
            it.setStatus(match.status, holder.status)
        }
        holder.itemView.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_teamDetailsTabLayoutFragment_to_matchDetailTabLayoutFragment,
                Bundle().apply { putInt("matchId", match.id) })
        }
    }
}