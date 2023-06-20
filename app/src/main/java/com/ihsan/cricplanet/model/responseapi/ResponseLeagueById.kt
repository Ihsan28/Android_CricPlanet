package com.ihsan.cricplanet.model.responseapi

import com.ihsan.cricplanet.model.league.LeagueIncludeSeasons

data class ResponseLeagueById(
    val `data`: LeagueIncludeSeasons
)