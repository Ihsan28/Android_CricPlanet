package com.ihsan.cricplanet.model.season

import com.ihsan.cricplanet.model.league.League

data class SeasonForCard(
    val code: String?,
    val fixtures: List<FixtureForSeason>?,
    val id: Int,
    val league: League?,
    val league_id: Int?,
    val name: String?,
    val resource: String?,
    val updated_at: String?
)