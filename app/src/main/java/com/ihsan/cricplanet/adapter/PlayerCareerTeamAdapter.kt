package com.ihsan.cricplanet.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.player.TeamIncludeInSquad
import com.ihsan.cricplanet.utils.Utils
import com.ihsan.cricplanet.viewmodel.CricViewModel

class PlayerCareerTeamAdapter(private val teamList: List<TeamIncludeInSquad>,private val viewModel:CricViewModel,private val viewLifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<PlayerCareerTeamAdapter.TeamViewHolder>() {

    class TeamViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        val teamName: TextView = binding.findViewById(R.id.team)
        val image: ImageView = itemView.findViewById(R.id.image)
        val source: TextView = itemView.findViewById(R.id.source)
        val teamRanking: TextView = itemView.findViewById(R.id.team_ranking)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.team_item, parent, false)
        Log.d("teamAdapter", "onCreateViewHolder: ${teamList.size}")
        return TeamViewHolder(root)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teamList[position]
        Log.d("cricteamAdapter", "BindViewHolderName: ${team.name}")


        team.in_squad?.season_id?.let {
            Log.d("cricteamAdapter", "BindViewHolderSeasonId: $it")
            viewModel.getSeasonByIdLocal(it).observe(viewLifecycleOwner){season->
                holder.source.text = season?.league_name ?:"Will Update Soon"
                holder.teamRanking.text = season?.name ?:""
                holder.teamName.text = team.name
                Utils().also {utils->
                    utils.loadImageWithPicasso(team.image_path, holder.image)
                }
            }
        }

        holder.itemView.setOnClickListener {

        }
    }

}