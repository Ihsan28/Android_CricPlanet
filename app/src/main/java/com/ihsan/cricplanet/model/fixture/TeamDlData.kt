package com.ihsan.cricplanet.model.fixture

data class TeamDlData(
    val overs: Any?,
    val score: Any?,
    val wickets_out: Any?
)
{
    constructor():this(
        null,
        null,
        null
    )
}