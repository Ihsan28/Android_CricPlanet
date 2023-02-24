package com.ihsan.cricplanet.adapter.grid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.PlayerGridItem

class PlayerDetailsGridAdapter (val context: Context, val items: List<PlayerGridItem>) : BaseAdapter() {

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
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.player_batting_bowling_grid_item, parent, false)
        val player=items[position]

        view.findViewById<TextView>(R.id.row_index_key).text=player.key
        view.findViewById<TextView>(R.id.row_index_value_1).text=player.value_1
        view.findViewById<TextView>(R.id.row_index_value_2).text = player.value_2
        view.findViewById<TextView>(R.id.row_index_value_3).text = player.value_3
        view.findViewById<TextView>(R.id.row_index_value_4).text = player.value_4
        return view
    }
}