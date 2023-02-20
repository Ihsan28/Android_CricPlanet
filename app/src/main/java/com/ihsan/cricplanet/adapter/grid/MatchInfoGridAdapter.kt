package com.ihsan.cricplanet.adapter.grid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.GridItem

class MatchInfoGridAdapter (val context: Context, val items: List<GridItem>) : BaseAdapter() {

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
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.match_info_grid_item, parent, false)

        val keyTextView = view.findViewById<TextView>(R.id.row_index_key)
        keyTextView.text = items[position].key

        val valueTextView = view.findViewById<TextView>(R.id.row_index_value)
        valueTextView.text = items[position].value

        return view
    }
}