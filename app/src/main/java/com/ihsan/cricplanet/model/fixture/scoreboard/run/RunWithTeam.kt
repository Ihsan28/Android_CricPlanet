package com.ihsan.cricplanet.model.fixture.scoreboard.run

import android.os.Parcelable
import com.ihsan.cricplanet.model.Team
import kotlinx.parcelize.Parcelize

@Parcelize
data class RunWithTeam(
    val fixture_id: Int?,
    val id: Int,
    val inning: Int?,
    val overs: Float?,
    val pp1: String?,
    val pp2: String?,
    val pp3: String?,
    val resource: String?,
    val score: Int?,
    val team_id: Int?,
    val team: Team?,
    val updated_at: String?,
    val wickets: Int?
) : Parcelable {
    constructor() : this(
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
        null
    )
}