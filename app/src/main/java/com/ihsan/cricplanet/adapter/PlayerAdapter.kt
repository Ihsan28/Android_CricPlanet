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
import com.ihsan.cricplanet.model.player.PlayerCard
import com.ihsan.cricplanet.utils.Utils
import java.util.*
import kotlin.collections.ArrayList

class PlayerAdapter(private val playerList: List<PlayerCard>) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {
    private var playerFilterList=playerList
    class PlayerViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        val playerName: TextView = itemView.findViewById(R.id.league_name)
        val playerAge: TextView = itemView.findViewById(R.id.league_type)
        val playerImage: ImageView = itemView.findViewById(R.id.league_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.player_card_item, parent, false)
        Log.d("teamAdapter", "onCreateViewHolder: ${playerFilterList.size}")
        return PlayerViewHolder(root)
    }

    override fun getItemCount(): Int {
        return playerFilterList.size
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged", "ResourceAsColor")
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playerFilterList[position]

        holder.playerName.text = player.fullname
        Utils().also {
            holder.playerAge.text = it.getPlayerAge(player.dateofbirth)
            it.loadImageWithPicasso(player.image_path, holder.playerImage)
        }

        holder.itemView.setOnClickListener {
            Log.d("cricMatchAdapter", "onBindViewHolder: ${player.id}")
            //Navigating adapter to player details
             Navigation.findNavController(holder.itemView)
                 .navigate(R.id.action_playerFragment_to_playerDetailsTabLayoutFragment,
                     Bundle().apply { putInt("playerId", player.id) })
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<PlayerCard>) {
        playerFilterList = list
        notifyDataSetChanged()
    }

    fun filter(text: String) {
        val filteredList = ArrayList<PlayerCard>()
        playerList.map {
            if (it.fullname.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT))
            ) {
                filteredList.add(it)
            }
        }
        updateList(filteredList)
    }
}