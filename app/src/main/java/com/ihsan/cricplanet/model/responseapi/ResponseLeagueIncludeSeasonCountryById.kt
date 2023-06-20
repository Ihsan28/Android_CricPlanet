package com.ihsan.cricplanet.model.responseapi

import com.ihsan.cricplanet.model.league.LeagueByIdIncludeSeasonCountry

data class ResponseLeagueIncludeSeasonCountryById(
    val `data`: LeagueByIdIncludeSeasonCountry
)