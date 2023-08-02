package com.ihsan.cricplanet.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.fixture.BowlingIncludeBowler
import com.ihsan.cricplanet.utils.Utils

class BowlingScorecardAdapter(private val bowlingScorecardList: List<BowlingIncludeBowler>) :
    RecyclerView.Adapter<BowlingScorecardAdapter.BattingViewHolder>() {
    class BattingViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        val playerName: TextView = itemView.findViewById(R.id.batting_name)
        val playerOver: TextView = itemView.findViewById(R.id.batting_run)
        val playerMedian: TextView = itemView.findViewById(R.id.batting_ball)
        val playerRunGiven: TextView = itemView.findViewById(R.id.batting_4s)
        val playerWicket: TextView = itemView.findViewById(R.id.batting_6s)
        val playerER: TextView = itemView.findViewById(R.id.batting_sr)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BattingViewHolder {
        val root = LayoutInflater.from(parent.context)
            .inflate(R.layout.batting_scorecard_item, parent, false)
        return BattingViewHolder(root)
    }

    override fun getItemCount(): Int {
        return bowlingScorecardList.size
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged", "ResourceAsColor")
    override fun onBindViewHolder(holder: BattingViewHolder, position: Int) {
        val scorecard = bowlingScorecardList[position]
        Log.d("teamAdapter", "BindViewHolder: ${bowlingScorecardList.size}")
        holder.playerName.text = scorecard.bowler?.fullname
        holder.playerOver.text = scorecard.overs.toString()
        holder.playerMedian.text = scorecard.medians.toString()
        holder.playerRunGiven.text = scorecard.runs.toString()
        holder.playerWicket.text = scorecard.wickets.toString()
        holder.playerER.text = Utils().oneDecimalPoint(scorecard.rate).toString()
    }
}