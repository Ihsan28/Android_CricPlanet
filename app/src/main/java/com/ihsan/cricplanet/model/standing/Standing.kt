package com.ihsan.cricplanet.model.standing

data class Standing(
    val draw: Int,
    val league_id: Int,
    val legend: Legend,
    val legend_id: Int,
    val lost: Int,
    val netto_run_rate: Double,
    val noresult: Int,
    val overs_against: Double,
    val overs_for: Double,
    val played: Int,
    val points: Int,
    val position: Int,
    val recent_form: List<String>,
    val resource: String,
    val runs_against: Int,
    val runs_for: Int,
    val season_id: Int,
    val stage_id: Int,
    val team_id: Int,
    val updated_at: String,
    val won: Int
)