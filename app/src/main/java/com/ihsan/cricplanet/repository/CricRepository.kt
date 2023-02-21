package com.ihsan.cricplanet.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.ihsan.cricplanet.model.Team
import com.ihsan.cricplanet.model.fixture.FixtureByIdWithDetails
import com.ihsan.cricplanet.model.fixture.FixtureIncludeForCard
import com.ihsan.cricplanet.model.fixture.FixtureIncludeForLiveCard
import com.ihsan.cricplanet.network.CricApi
import com.ihsan.cricplanet.roomdb.dao.CricDao
import com.ihsan.cricplanet.utils.Constant
import com.ihsan.cricplanet.utils.Utils

class CricRepository(private val cricDao: CricDao) {
    fun readTeams(): LiveData<List<Team>> {
        return cricDao.readTeams()
    }

    suspend fun storeTeamsLocal(team: List<Team>) {
        return cricDao.storeTeams(team)
    }

    suspend fun getTeamsApi(): List<Team> {
        return CricApi.retrofitService.getTeamsResponse(Constant.API_KEY).data
    }

    suspend fun getFixturesApi(): List<FixtureIncludeForCard> {
        return CricApi.retrofitService.getFixturesResponse(
            "2022-01-15,2024-02-13",
            "",
            "localteam,visitorteam,venue.country,season,league",
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
            "localteam,visitorteam,venue.country,season,league",
            "starting_at",
            Constant.API_KEY
        ).data
    }

    suspend fun getUpcomingFixturesApi(): List<FixtureIncludeForCard> {
        return CricApi.retrofitService.getFixturesResponse(
            Utils().upcomingYearDuration(),
            "NS",
            "localteam,visitorteam,venue,season,league",
            "starting_at",
            Constant.API_KEY
        ).data
    }

    suspend fun getRecentFixturesApi(): List<FixtureIncludeForCard> {
        return CricApi.retrofitService.getFixturesResponse(
            Utils().recentMonthDuration(),
            "Finished",
            "localteam,visitorteam,venue,season,league",
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
                    "localteam.country, visitorteam.country,visitorteam.results",
            Constant.API_KEY
        ).data
    }
}