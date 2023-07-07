package com.ihsan.cricplanet.model.player.careerstats

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Batting(
    var matches: Int?,
    var innings: Int?,
    var runs_scored: Int?,
    var not_outs: Int?,
    var highest_inning_score: Int?,
    var strike_rate: Double?,
    var balls_faced: Int?,
    var average: Double?,
    var four_x: Int?,
    var six_x: Int?,
    var fifties: Int?,
    var hundreds: Int?,
    val fow_balls: Double?,
    val fow_score: Int?,
) : Parcelable {
    constructor() : this(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
    )
}