package com.ihsan.cricplanet.roomdb.data

import com.ihsan.cricplanet.model.SeasonByIdIncludeLeague
import com.ihsan.cricplanet.model.SeasonByIdIncludeLeagueTable

class DataConverter {

    fun getSeasonTable(listSeason: List<SeasonByIdIncludeLeague>?): List<SeasonByIdIncludeLeagueTable> {
        return listSeason!!.map{ season->
            SeasonByIdIncludeLeagueTable(
                season.id,
                season.name,
                season.league?.code,
                season.league?.image_path,
                season.league?.name,
                season.league?.type
            )
        }
    }

}