package com.ihsan.cricplanet.adapter.grid

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.PlayerGridItem

private const val TAG = "PlayerDetailsGridAdapte"
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

        Log.d(TAG, "getView: key: ${player.key} values: ${player.value}")

        // Get the parent layout
        val parentLayout: LinearLayout = view.findViewById(R.id.row_index)
        parentLayout.removeAllViews()

        val keyTextView = TextView(context).apply {
            id = View.generateViewId()
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.START
                weight = 2.7f
            }
            text = player.key
            textSize = 12f
            setTextColor(ContextCompat.getColor(context, R.color.md_blue_50))
            setTypeface(null, Typeface.BOLD)
        }

        // Add the TextView to the parent layout
        parentLayout.addView(keyTextView)

        // Add children views dynamically based on the number of items
        for (i in 0 until player.value.size) {


            // Create a new TextView instance
            val textView = TextView(context).apply {
                id = View.generateViewId()
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.CENTER
                    weight = 3f
                }
                text = player.value[i]
                textSize = 12f
                setTextColor(ContextCompat.getColor(context, R.color.md_blue_50))

                setTypeface(null, Typeface.BOLD_ITALIC)

            }

            // Add the TextView to the parent layout
            parentLayout.addView(textView)
        }
        return view
    }
}