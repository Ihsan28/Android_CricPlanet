package com.ihsan.cricplanet.model.team

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GlobalTeamRanking(
    val gender: String?,
    val points: Int?,
    val position: Int?,
    val rating: Int?,
    val resource: String?,
    val team: List<TeamIncludeRanking>?,
    val type: String?,
    val updated_at: String?
):Parcelable
