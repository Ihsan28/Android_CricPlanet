package com.ihsan.cricplanet.model.responseapi

import com.ihsan.cricplanet.model.SeasonByIdIncludeLeague

data class ResponseSeason(
    val `data`: List<SeasonByIdIncludeLeague>
)