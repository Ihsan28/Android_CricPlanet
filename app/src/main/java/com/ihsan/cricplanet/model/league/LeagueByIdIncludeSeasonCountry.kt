package com.ihsan.cricplanet.model.league

import android.os.Parcelable
import com.ihsan.cricplanet.model.Country
import com.ihsan.cricplanet.model.season.Season
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueByIdIncludeSeasonCountry(
    val code: String?,
    val country: Country?,
    val country_id: Int?,
    val id: Int,
    val image_path: String?,
    val name: String?,
    val resource: String?,
    val season: Season?,
    val season_id: Int?,
    val type: String?,
    val updated_at: String?
): Parcelable