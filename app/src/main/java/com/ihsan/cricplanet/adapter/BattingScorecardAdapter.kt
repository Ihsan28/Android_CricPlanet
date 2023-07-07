package com.ihsan.cricplanet.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.fixture.BattingIncludeBatsman

class BattingScorecardAdapter(private val battingScorecardList: List<BattingIncludeBatsman>) :
    RecyclerView.Adapter<BattingScorecardAdapter.BattingViewHolder>() {
    class BattingViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        val playerName: TextView = itemView.findViewById(R.id.batting_name)
        val playerRun: TextView = itemView.findViewById(R.id.batting_run)
        val playerBall: TextView = itemView.findViewById(R.id.batting_ball)
        val player4s: TextView = itemView.findViewById(R.id.batting_4s)
        val player6s: TextView = itemView.findViewById(R.id.batting_6s)
        val playerSR: TextView = itemView.findViewById(R.id.batting_sr)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BattingViewHolder {
        val root = LayoutInflater.from(parent.context)
            .inflate(R.layout.batting_scorecard_item, parent, false)
        return BattingViewHolder(root)
    }

    override fun getItemCount(): Int {
        return battingScorecardList.size
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged", "ResourceAsColor")
    override fun onBindViewHolder(holder: BattingViewHolder, position: Int) {
        val scorecard = battingScorecardList[position]
        Log.d("teamAdapter", "BindViewHolder: ${battingScorecardList.size}")
        holder.playerName.text = scorecard.batsman?.fullname
        holder.playerRun.text = scorecard.score.toString()
        holder.playerBall.text = scorecard.ball.toString()
        holder.player4s.text = scorecard.four_x.toString()
        holder.player6s.text = scorecard.six_x.toString()
        holder.playerSR.text = scorecard.rate.toString()
    }
}