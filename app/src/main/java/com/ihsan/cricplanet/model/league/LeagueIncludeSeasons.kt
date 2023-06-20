package com.ihsan.cricplanet.model.league

import android.os.Parcelable
import com.ihsan.cricplanet.model.season.Season
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueIncludeSeasons(
    val code: String?,
    val country_id: Int?,
    val id: Int,
    val image_path: String?,
    val name: String?,
    val resource: String?,
    val season_id: Int?,
    val type: String?,
    val season: Season?,
    val seasons: List<Season>?,
    val updated_at: String?
):Parcelable
{
    constructor():this(
        null,
        null,
        0,
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