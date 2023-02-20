package com.ihsan.cricplanet.model

import com.ihsan.cricplanet.model.fixture.scoreboard.lineup.Lineup

data class SquadItem(
    val localPlayer:Lineup?,
    val visitorPlayer:Lineup?
)
{
    constructor():this(
        null,
        null
    )
}
