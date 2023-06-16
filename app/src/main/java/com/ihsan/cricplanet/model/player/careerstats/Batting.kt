package com.ihsan.cricplanet.model.player.careerstats

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Batting(
    var average: Double?,
    var balls_faced: Int?,
    var fifties: Int?,
    var four_x: Int?,
    val fow_balls: Double?,
    val fow_score: Int?,
    var highest_inning_score: Int?,
    var hundreds: Int?,
    var innings: Int?,
    var matches: Int?,
    var not_outs: Int?,
    var runs_scored: Int?,
    var six_x: Int?,
    var strike_rate: Double?
):Parcelable
{
    constructor():this(
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