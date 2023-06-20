package com.ihsan.cricplanet.model.season

import com.ihsan.cricplanet.model.league.League

data class SeasonByIdIncludeLeague(
    val code: String?,
    val id: Int,
    val league: League?,
    val league_id: Int?,
    val name: String?,
    val resource: String?,
    val updated_at: String?
) {
    constructor() : this(
        null,
        0,
        null,
        null,
        null,
        null,
        null,
    )
}