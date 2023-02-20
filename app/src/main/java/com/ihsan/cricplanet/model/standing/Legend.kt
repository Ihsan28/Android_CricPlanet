package com.ihsan.cricplanet.model.standing

data class Legend(
    val description: String,
    val id: Int,
    val league_id: Int,
    val position: Int,
    val resource: String,
    val season_id: Int,
    val stage_id: Int,
    val updated_at: String
)