package com.ihsan.cricplanet.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.league.LeagueIncludeSeasons
import com.ihsan.cricplanet.utils.MyApplication
import com.ihsan.cricplanet.utils.Utils

class LeagueAdapter(
    private val leagueList: List<LeagueIncludeSeasons>,
    private val requireActivity: Activity
) :
    RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {

    class LeagueViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        val leagueName: TextView = itemView.findViewById(R.id.league_name)
        val leagueType: TextView = itemView.findViewById(R.id.league_type)
        val leagueImage: ImageView = itemView.findViewById(R.id.league_image)
        val leagueCard: CardView = itemView.findViewById(R.id.card_view_league)
        val leagueRecyclerView: RecyclerView =
            itemView.findViewById(R.id.recyclerview_league_seasons)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.league_card_item, parent, false)
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

        //Season adapter builder function called
        buildSeasonAdapter(holder.leagueRecyclerView, league)

        holder.leagueName.text = league.name
        holder.leagueType.text = league.type
        Utils().also {
            it.loadImageWithPicasso(league.image_path, holder.leagueImage)
        }
        val recyclerView = holder.leagueRecyclerView
        val resources = MyApplication.instance.resources

        holder.leagueCard.setOnClickListener {
            val currentHeight = recyclerView.layoutParams.height

            if (currentHeight == resources.getDimensionPixelSize(R.dimen.league_card_minimize_height)) {
                // If the current height match, set the height to wrap_content
                recyclerView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            } else {
                // If the current height is wrap_content, set the height to mini height
                recyclerView.layoutParams.height =
                    resources.getDimensionPixelSize(R.dimen.league_card_minimize_height)
            }
            // updated to the RecyclerView
            recyclerView.layoutParams = recyclerView.layoutParams
        }
    }

    private fun buildSeasonAdapter(recyclerView: RecyclerView, league: LeagueIncludeSeasons) {
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter =
            league.seasons?.let { SeasonAdapter(it.reversed(), league.image_path, league.code) }
    }
}