package com.ihsan.cricplanet.model.player.careerstats

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bowling(
    var average: Double?,
    var econ_rate: Double?,
    var five_wickets: Int?,
    var four_wickets: Int?,
    var innings: Int?,
    var matches: Int?,
    var medians: Int?,
    var noball: Int?,
    var overs: Double?,
    var rate: Double?,
    var runs: Int?,
    var strike_rate: Double?,
    var ten_wickets: Int?,
    var wickets: Int?,
    var wide: Int?
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
        null
    )
}
