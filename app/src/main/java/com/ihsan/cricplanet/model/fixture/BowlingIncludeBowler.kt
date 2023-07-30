package com.ihsan.cricplanet.model.fixture

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BowlingIncludeBowler(
    val active: Boolean?,
    val bowler: Bowler?,
    val fixture_id: Int?,
    val id: Int,
    val medians: Int?,
    val noball: Int?,
    val overs: Double?,
    val player_id: Int?,
    val rate: Double?,
    val resource: String?,
    val runs: Int?,
    val scoreboard: String?,
    val sort: Int?,
    val team_id: Int?,
    val updated_at: String?,
    val wickets: Int?,
    val wide: Int?
):Parcelable