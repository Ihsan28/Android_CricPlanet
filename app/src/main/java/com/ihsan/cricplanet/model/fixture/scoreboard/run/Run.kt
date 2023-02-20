package com.ihsan.cricplanet.model.fixture.scoreboard.run

data class Run(
    val fixture_id: Int?,
    val id: Int,
    val inning: Int?,
    val overs: Float?,
    val pp1: String?,
    val pp2: Any?,
    val pp3: Any?,
    val resource: String?,
    val score: Int?,
    val team_id: Int?,
    val updated_at: String?,
    val wickets: Int?
)
{
    constructor():this(
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
        null
    )
}