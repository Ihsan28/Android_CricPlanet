package com.ihsan.cricplanet.model.responseapi

import com.ihsan.cricplanet.model.LeagueIncludeSeasons
import com.ihsan.cricplanet.model.SeasonByIdIncludeLeague

data class ResponseSeasonById(
    val `data`: SeasonByIdIncludeLeague
)