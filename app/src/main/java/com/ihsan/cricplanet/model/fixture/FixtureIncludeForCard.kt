package com.ihsan.cricplanet.model.fixture

import com.ihsan.cricplanet.model.League
import com.ihsan.cricplanet.model.Season
import com.ihsan.cricplanet.model.Team
import com.ihsan.cricplanet.model.VenueIncludeCountry
import com.ihsan.cricplanet.model.fixture.scoreboard.run.RunWithTeam

data class FixtureIncludeForCard(
    val draw_noresult: Any?,
    val elected: Any?,
    val first_umpire_id: Any?,
    val follow_on: Boolean?,
    val id: Int,
    val last_period: Any?,
    val league: League?,
    val league_id: Int?,
    val live: Boolean?,
    val localteam: Team?,
    val localteam_dl_data: TeamDlData?,
    val localteam_id: Int?,
    val man_of_match_id: Any?,
    val man_of_series_id: Any?,
    val note: String?,
    val referee_id: Any?,
    val resource: String?,
    val round: String?,
    val rpc_overs: Any?,
    val rpc_target: Any?,
    val runs: List<RunWithTeam>?,
    val season: Season?,
    val season_id: Int?,
    val second_umpire_id: Any?,
    val stage_id: Int?,
    val starting_at: String?,
    val status: String?,
    val super_over: Boolean?,
    val toss_won_team_id: Any?,
    val total_overs_played: Any?,
    val tv_umpire_id: Any?,
    val type: String?,
    val venue: VenueIncludeCountry?,
    val venue_id: Int?,
    val visitorteam: Team?,
    val visitorteam_dl_data: TeamDlData?,
    val visitorteam_id: Int?,
    val weather_report: List<Any>?,
    val winner_team_id: Any?
) {
    constructor() : this(
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
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )
}