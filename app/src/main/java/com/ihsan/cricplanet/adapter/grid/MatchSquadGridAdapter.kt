package com.ihsan.cricplanet.adapter.grid

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.fixture.scoreboard.lineup.Lineup
import com.ihsan.cricplanet.utils.Utils

data class MatchSquadGridAdapter(val context: Context, val items: List<Lineup>) : BaseAdapter() {

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
        val player=items[position]
        val keyTextView = view.findViewById<TextView>(R.id.left_player)
        val playerImage=view.findViewById<ImageView>(R.id.player_image)
        keyTextView.text = player.fullname

        Utils().also {
            it.loadImageWithPicasso(player.image_path,playerImage)
        }

        return view
    }
}
