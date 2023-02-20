package com.ihsan.cricplanet.model.fixture.scoreboard

import android.os.Parcelable
import com.ihsan.cricplanet.model.Team
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Scoreboard(
    val bye: Int?,
    val fixture_id: Int?,
    val id: Int,
    val leg_bye: Int?,
    val noball_balls: Int?,
    val noball_runs: Int?,
    val overs: Float?,
    val penalty: Int?,
    val resource: String?,
    val scoreboard: String?,
    val team: Team?,
    val team_id: Int?,
    val total: Int?,
    val type: String?,
    val updated_at: String?,
    val wickets: Int?,
    val wide: Int?
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
        null,
        null,
        null,
        null,
        null,
        null,
    )
}