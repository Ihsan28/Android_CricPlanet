package com.ihsan.cricplanet.adapter.grid

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.team.Squad
import com.ihsan.cricplanet.utils.Utils

data class TeamSquadAdapter(val context: Context, val items: List<Squad>) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        Log.d("cricMatchSquadAdapter", "getView: ${items.size}")
        val view: View = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.match_squad_grid_item, parent, false)
        val player = items[position]
        val playerName = view.findViewById<TextView>(R.id.left_player)
        val playerAge = view.findViewById<TextView>(R.id.league_type)
        val playerPosition = view.findViewById<TextView>(R.id.player_position)
        val playerImage = view.findViewById<ImageView>(R.id.league_image)

        playerName.text = player.fullname
        playerPosition.text = player.position?.name

        Utils().also {
            it.loadImageWithPicasso(player.image_path, playerImage)
            playerAge.text = player.dateofbirth?.let { it1 -> it.getPlayerAge(it1) }
        }

        view.setOnClickListener {
            Log.d("cricMatchAdapter", "onBindViewHolder: ${player.id}")
            //Navigating adapter to player details
            Navigation.findNavController(view)
                .navigate(R.id.action_teamDetailsTabLayoutFragment_to_playerDetailsTabLayoutFragment,
                    Bundle().apply { putInt("playerId", player.id) })
        }

        return view
    }
}
