package com.ihsan.cricplanet.adapter.grid

import android.content.Context
import android.graphics.Typeface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.PlayerGridItem

class PlayerDetailsGridAdapter(val context: Context, val items: List<PlayerGridItem>) :
    BaseAdapter() {

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
        val view: View = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.player_batting_bowling_grid_item, parent, false)
        val player = items[position]

        view.findViewById<TextView>(R.id.row_index_key).text = player.key

        // Add children views dynamically based on the number of items
        for (i in 0 until player.value.size) {
            // Get the parent layout
            val parentLayout: LinearLayout = view.findViewById(R.id.row_index)

            // Create a new TextView instance
            val textView = TextView(context).apply {
                id = View.generateViewId()
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.START
                    weight = 3f
                }
                text = player.value[i]
                textSize = 12f
                setTextColor(ContextCompat.getColor(context, R.color.md_blue_50))
                setTypeface(null, Typeface.BOLD)
            }

            // Add the TextView to the parent layout
            parentLayout.addView(textView)
        }

        /*view.findViewById<TextView>(R.id.row_index_value_1).text=player.value_1
        view.findViewById<TextView>(R.id.row_index_value_2).text = player.value_2
        view.findViewById<TextView>(R.id.row_index_value_3).text = player.value_3
        view.findViewById<TextView>(R.id.row_index_value_4).text = player.value_4*/
        return view
    }
}