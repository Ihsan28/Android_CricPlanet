package com.ihsan.cricplanet.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.season.Season
import com.ihsan.cricplanet.utils.Utils

class SeasonAdapter(private val seasonList: List<Season>, private val leagueImage:String?, private val leagueCode:String?) :
    RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder>() {

    class SeasonViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        val seasonName: TextView = itemView.findViewById(R.id.season_name)
        val leagueCode: TextView = itemView.findViewById(R.id.season_league_code)
        val leagueImage: ImageView = itemView.findViewById(R.id.season_league_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.season_card_item, parent, false)
        Log.d("cricLeagueAdapter", "onCreateViewHolder: ${seasonList.size}")
        return SeasonViewHolder(root)
    }

    override fun getItemCount(): Int {
        return seasonList.size
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged", "ResourceAsColor")
    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val season = seasonList[position]
        Log.d("cricLeagueAdapter", "onBindViewHolder: $season")
        holder.seasonName.text = season.name
        holder.leagueCode.text = leagueCode ?: ""
        Utils().also {
            it.loadImageWithPicasso(leagueImage, holder.leagueImage)
        }

        holder.itemView.setOnClickListener {
            Log.d("cricMatchAdapter", "onBindViewHolder: ${season.id}")

             Navigation.findNavController(holder.itemView)
                 .navigate(R.id.action_seriesFragment_to_matchesFragment,
                     Bundle().apply { putString("category", season.id.toString()) })
        }
    }
}