package com.ihsan.cricplanet.model.team

data class GlobalTeamRanking(
    val gender: String?,
    val points: Any?,
    val position: Any?,
    val rating: Any?,
    val resource: String?,
    val team: List<TeamIncludeRanking>?,
    val type: String?,
    val updated_at: String?
)
{
    constructor():this(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
    )
}