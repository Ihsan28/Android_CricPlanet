package com.ihsan.cricplanet.model

data class SeasonByIdIncludeLeague(
    val code: String?,
    val id: Int,
    val league: League?,
    val league_id: Int?,
    val name: String?,
    val resource: String?,
    val updated_at: String?
)