package com.ihsan.cricplanet.model.player

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val code: String?,
    val country_id: Int?,
    val id: Int,
    val image_path: String?,
    val in_squad: InSquad?,
    val name: String?,
    val national_team: Boolean?,
    val resource: String?,
    val updated_at: String?
): Parcelable
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
    )
}