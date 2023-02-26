package com.ihsan.cricplanet.model.fixture

import android.os.Parcelable
import androidx.room.Ignore
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fixture(
    val draw_noresult: String?,
    val elected: String?,
    val first_umpire_id: Int?,
    val follow_on: Boolean?,
    val id: Int,
    @Ignore val last_period: String?,
    val league_id: Int?,
    val live: Boolean?,
    @Ignore val localteam_dl_data: TeamDlData?,
    val localteam_id: Int?,
    val man_of_match_id: Int?,
    val man_of_series_id: Int?,
    val note: String?,
    val referee_id: Int?,
    val resource: String?,
    val round: String?,
    val rpc_overs: Double?,
    val rpc_target: Int?,
    val season_id: Int?,
    val second_umpire_id: Int?,
    val stage_id: Int?,
    val starting_at: String?,
    val status: String?,
    val super_over: Boolean?,
    val toss_won_team_id: Int?,
    val total_overs_played: Double?,
    val tv_umpire_id: Int?,
    val type: String?,
    val venue_id: Int?,
    @Ignore val visitorteam_dl_data: TeamDlData?,
    val visitorteam_id: Int?,
    @Ignore val weather_report: List<String>?,
    val winner_team_id: Int?
):Parcelable
{
    constructor():this(
        null,
        null,
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
        null
    )
}