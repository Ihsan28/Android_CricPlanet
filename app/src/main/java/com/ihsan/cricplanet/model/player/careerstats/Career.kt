package com.ihsan.cricplanet.model.player.careerstats

data class Career(
    val batting: Batting,
    val bowling: Any,
    val player_id: Int,
    val resource: String,
    val season_id: Int,
    val type: String,
    val updated_at: String
)