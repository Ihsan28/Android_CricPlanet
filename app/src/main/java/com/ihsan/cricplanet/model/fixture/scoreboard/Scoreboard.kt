package com.ihsan.cricplanet.model.fixture.scoreboard

import com.ihsan.cricplanet.model.Team

data class Scoreboard(
    val bye: Int?,
    val fixture_id: Int?,
    val id: Int,
    val leg_bye: Int?,
    val noball_balls: Int?,
    val noball_runs: Int?,
    val overs: Int?,
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
)
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