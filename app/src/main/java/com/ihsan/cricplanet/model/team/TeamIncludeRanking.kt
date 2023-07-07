package com.ihsan.cricplanet.model.team

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamIncludeRanking(
    val code: String?,
    val country_id: Int?,
    val id: Int,
    val image_path: String?,
    val name: String?,
    val national_team: Boolean?,
    val position: Int?,
    val ranking: Ranking?,
    val resource: String?,
    val updated_at: String?
) : Parcelable
