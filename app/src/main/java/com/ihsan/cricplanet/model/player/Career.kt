package com.ihsan.cricplanet.model.player

import android.os.Parcelable
import com.ihsan.cricplanet.model.season.Season
import com.ihsan.cricplanet.model.player.careerstats.Batting
import kotlinx.parcelize.Parcelize

@Parcelize
data class Career(
    val batting: Batting?,
    val bowling: Bowling?,
    val player_id: Int?,
    val resource: String?,
    val season: Season?,
    val season_id: Int?,
    val type: String?,
    val updated_at: String?
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
        null
    )
}