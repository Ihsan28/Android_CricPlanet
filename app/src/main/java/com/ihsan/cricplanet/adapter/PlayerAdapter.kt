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
import com.ihsan.cricplanet.model.player.PlayerCard
import com.ihsan.cricplanet.utils.Utils

class PlayerAdapter(private val playerList: List<PlayerCard>) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    class PlayerViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        val playerName: TextView = itemView.findViewById(R.id.league_name)
        val playerAge: TextView = itemView.findViewById(R.id.league_type)
        val playerImage: ImageView = itemView.findViewById(R.id.league_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.player_card_item, parent, false)
        Log.d("teamAdapter", "onCreateViewHolder: ${playerList.size}")
        return PlayerViewHolder(root)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged", "ResourceAsColor")
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playerList[position]

        holder.playerName.text = player.fullname
        Utils().also {
            holder.playerAge.text = it.getPlayerAge(player.dateofbirth)
            it.loadImageWithPicasso(player.image_path, holder.playerImage)
        }

        holder.itemView.setOnClickListener {
            Log.d("cricMatchAdapter", "onBindViewHolder: ${player.id}")
            //crash issue
            /* Navigation.findNavController(holder.itemView)
                 .navigate(R.id.action_matchTabLayoutFragment_to_matchDetailTabLayoutFragment,
                     Bundle().apply { putInt("matchId", player.id) })*/
        }
    }
}