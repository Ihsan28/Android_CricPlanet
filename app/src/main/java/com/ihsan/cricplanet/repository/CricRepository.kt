package com.ihsan.cricplanet.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.ihsan.cricplanet.model.*
import com.ihsan.cricplanet.model.fixture.FixtureByIdWithDetails
import com.ihsan.cricplanet.model.fixture.FixtureIncludeForCard
import com.ihsan.cricplanet.model.fixture.FixtureIncludeForLiveCard
import com.ihsan.cricplanet.model.player.PlayerCard
import com.ihsan.cricplanet.model.player.PlayerDetails
import com.ihsan.cricplanet.model.season.SeasonByIdIncludeLeague
import com.ihsan.cricplanet.model.season.SeasonByIdIncludeLeagueTable
import com.ihsan.cricplanet.model.season.SeasonForCard
import com.ihsan.cricplanet.model.team.GlobalTeamRanking
import com.ihsan.cricplanet.model.team.TeamDetails
import com.ihsan.cricplanet.network.CricApi
import com.ihsan.cricplanet.roomdb.dao.CricDao
import com.ihsan.cricplanet.utils.Constant
import com.ihsan.cricplanet.utils.Utils

class CricRepository(private val cricDao: CricDao) {

    fun readSeason(): LiveData<List<SeasonByIdIncludeLeagueTable>> {
        return cricDao.readSeason()
    }
    fun readSeasonById(id:Int): LiveData<SeasonByIdIncludeLeagueTable> {
        return cricDao.readSeasonById(id)
    }

    suspend fun storeSeasonLocal(season: List<SeasonByIdIncludeLeagueTable>) {
        return cricDao.storeSeason(season)
    }

    suspend fun getTeamRankingApi(): List<GlobalTeamRanking> {
        return CricApi.retrofitService.getTeamRankingResponse(Constant.API_KEY).data
    }

    suspend fun getFixturesApi(): List<FixtureIncludeForCard> {
        return CricApi.retrofitService.getFixturesResponse(
            "2022-01-15,2024-02-13",
            "",
            "localteam,visitorteam,venue.country,season,league,runs.team",
            "starting_at",
            Constant.API_KEY
        ).data
    }

    suspend fun getLiveFixturesApi(): List<FixtureIncludeForLiveCard> {
        Log.d(
            "cricRepository", "getLiveFixturesApi: ${
                CricApi.retrofitService.getLiveFixturesResponse(
                    "localteam,visitorteam,venue,season,league", "starting_at", Constant.API_KEY
                ).data
            }"
        )
        return CricApi.retrofitService.getLiveFixturesResponse(
            "localteam,visitorteam,venue,season,league", "starting_at", Constant.API_KEY
        ).data
    }

    suspend fun getTodayFixturesApi(): List<FixtureIncludeForCard> {
        return CricApi.retrofitService.getTodayFixturesResponse(
            Utils().getCurrentDate(),
            "localteam,visitorteam,venue.country,season,league,runs.team",
            "starting_at",
            Constant.API_KEY
        ).data
    }

    suspend fun getUpcomingFixturesApi(): List<FixtureIncludeForCard> {
        return CricApi.retrofitService.getFixturesResponse(
            Utils().upcomingYearDuration(),
            "NS",
            "localteam,visitorteam,venue,season,league,runs.team",
            "starting_at",
            Constant.API_KEY
        ).data
    }

    suspend fun getRecentFixturesApi(): List<FixtureIncludeForCard> {
        return CricApi.retrofitService.getFixturesResponse(
            Utils().recentMonthDuration(),
            "Finished",
            "localteam,visitorteam,venue,season,league,runs.team",
            "starting_at",
            Constant.API_KEY
        ).data
    }

    //visitorteam.results,scoreboards.team
    suspend fun getFixturesByIdApi(Id: Int): FixtureByIdWithDetails {
        return CricApi.retrofitService.getFixtureByIdResponse(
            Id,
            "scoreboards.team,runs.team,tosswon,manofmatch,manofseries,venue.country," +
                    "lineup,winnerteam,season,league,referee,firstumpire,secondumpire,tvumpire," +
                    "localteam.country, visitorteam.country,visitorteam.results,balls.bowler,batting.batsman",
            Constant.API_KEY
        ).data
    }

    suspend fun getPlayersApi(): List<PlayerCard> {
        return CricApi.retrofitService.getPlayersResponse(
            "id,fullname,image_path,dateofbirth",
            Constant.API_KEY
        ).data
    }
    suspend fun getPlayersByIdApi(Id: Int): PlayerDetails {
        return CricApi.retrofitService.getPlayerByIdResponse(
            Id,
            "country, career, career.season, teams, currentteams",
            Constant.API_KEY
        ).data
    }
    suspend fun getLeaguesApi(): List<LeagueIncludeSeasons> {
        return CricApi.retrofitService.getLeaguesResponse(
            "season,seasons",
            Constant.API_KEY
        ).data
    }
    suspend fun getLeaguesByIdApi(Id: Int):LeagueIncludeSeasons {
        return CricApi.retrofitService.getLeagueByIdResponse(
            Id,
            "season,seasons",
            Constant.API_KEY
        ).data
    }
    suspend fun getSeasonApi():List<SeasonByIdIncludeLeague> {
        return CricApi.retrofitService.getSeasonResponse(
            "league",
            Constant.API_KEY
        ).data
    }

    suspend fun getSeasonByIdApi(Id: Int): SeasonForCard {
        return CricApi.retrofitService.getSeasonByIdResponse(
            Id,
            "fixtures.localteam, fixtures.visitorteam,league",
            Constant.API_KEY
        ).data
    }

    suspend fun getTeamByIdApi(Id: Int): TeamDetails {
        return CricApi.retrofitService.getTeamByIdResponse(
            Id,
            "country,fixtures,results,squad.position",
            Constant.API_KEY
        ).data
    }
}