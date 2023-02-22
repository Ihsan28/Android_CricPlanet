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
import com.ihsan.cricplanet.model.League
import com.ihsan.cricplanet.utils.Utils

class LeagueAdapter(private val leagueList: List<League>) :
    RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {

    class LeagueViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        val playerName: TextView = itemView.findViewById(R.id.player_name)
        val playerAge: TextView = itemView.findViewById(R.id.player_age)
        val playerImage: ImageView = itemView.findViewById(R.id.player_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.player_card_item, parent, false)
        Log.d("cricLeagueAdapter", "onCreateViewHolder: ${leagueList.size}")
        return LeagueViewHolder(root)
    }

    override fun getItemCount(): Int {
        return leagueList.size
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged", "ResourceAsColor")
    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val league = leagueList[position]
        Log.d("cricLeagueAdapter", "onBindViewHolder: $league")
        holder.playerName.text = league.name
        holder.playerAge.text = league.type
        Utils().also {
            it.loadImageWithPicasso(league.image_path, holder.playerImage)
        }

        holder.itemView.setOnClickListener {
            Log.d("cricMatchAdapter", "onBindViewHolder: ${league.id}")
            //crash issue
            /* Navigation.findNavController(holder.itemView)
                 .navigate(R.id.action_matchTabLayoutFragment_to_matchDetailTabLayoutFragment,
                     Bundle().apply { putInt("matchId", player.id) })*/
        }
    }
}