package com.ihsan.cricplanet.model.responseapi

import com.ihsan.cricplanet.model.LeagueIncludeSeasons

data class ResponseLeagues(
    val `data`: List<LeagueIncludeSeasons>
)