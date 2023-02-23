package com.ihsan.cricplanet.model.player

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bowling(
    val average: Int?,
    val econ_rate: Double?,
    val five_wickets: Int?,
    val four_wickets: Int?,
    val innings: Int?,
    val matches: Int?,
    val medians: Int?,
    val noball: Int?,
    val overs: Int?,
    val rate: Double?,
    val runs: Int?,
    val strike_rate: Int?,
    val ten_wickets: Int?,
    val wickets: Int?,
    val wide: Int?
): Parcelable
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
        null
    )
}