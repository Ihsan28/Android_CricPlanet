package com.ihsan.cricplanet.model.player

import android.os.Parcelable
import com.ihsan.cricplanet.model.Position
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerDetails(
    val battingstyle: String?,
    val bowlingstyle: String?,
    val career: List<Career>?,
    val country: Country?,
    val country_id: Int?,
    val currentteams: List<Team>?,
    val dateofbirth: String?,
    val firstname: String?,
    val fullname: String?,
    val gender: String?,
    val id: Int,
    val image_path: String?,
    val lastname: String?,
    val position: Position?,
    val resource: String?,
    val teams: List<Team>?,
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
        null,
        null,
        null,
        0,
        null,
        null,
        null,
        null,
        null,
        null
    )
}