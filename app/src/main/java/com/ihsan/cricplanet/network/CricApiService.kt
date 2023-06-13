package com.ihsan.cricplanet.network

import com.ihsan.cricplanet.model.responseapi.*
import com.ihsan.cricplanet.utils.Constant
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://cricket.sportmonks.com/api/v2.0/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
val client = OkHttpClient.Builder()
    .connectTimeout(10, TimeUnit.SECONDS) // connection timeout 10 seconds
    .readTimeout(30, TimeUnit.SECONDS) // read timeout 30 seconds
    .writeTimeout(30, TimeUnit.SECONDS) // write timeout 30 seconds
    .build()

private val retrofit = Retrofit
    .Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL)
    .client(client)
    .build()

interface CricApiService {
    @GET(Constant.rankings)
    suspend fun getTeamRankingResponse(
        @Query(Constant.api_token) api_token: String
    ): ResponseGlobalTeamRanking

    @GET(Constant.fixtures)
    suspend fun getFixturesResponse(
        @Query(Constant.filterDate) startsBetween: String,
        @Query(Constant.filterByStatus) filterByStatus: String,
        @Query(Constant.include) include: String,
        @Query(Constant.sort) sort: String,
        @Query(Constant.api_token) api_token: String
    ): ResponseFixtureIncludeForCard

    @GET(Constant.fixtures)
    suspend fun getTodayFixturesResponse(
        @Query(Constant.filterDate) startsBetween: String,
        @Query(Constant.include) include: String,
        @Query(Constant.sort) sort: String,
        @Query(Constant.api_token) api_token: String
    ): ResponseFixtureIncludeForCard

    @GET(Constant.liveScores)
    suspend fun getLiveFixturesResponse(
        @Query(Constant.include) include: String,
        @Query(Constant.sort) sort: String,
        @Query(Constant.api_token) api_token: String
    ): ResponseFixtureIncludeForLiveCard

    @GET("fixtures/{id}")
    suspend fun getFixtureByIdResponse(
        @Path("id") id: Int,
        @Query(Constant.include) include: String,
        @Query(Constant.api_token) apiToken: String
    ): ResponseFixtureById

    @GET(Constant.players)
    suspend fun getPlayersResponse(
        @Query(Constant.fieldsPlayer) fields: String,
        @Query(Constant.api_token) api_token: String
    ): ResponsePlayerCard

    @GET("${Constant.players}/{id}")
    suspend fun getPlayerByIdResponse(
        @Path("id") id: Int,
        @Query(Constant.include) include: String,
        @Query(Constant.api_token) apiToken: String
    ): ResponsePlayerCardDetails

    @GET(Constant.league)
    suspend fun getLeaguesResponse(
        @Query(Constant.include) include: String,
        @Query(Constant.api_token) api_token: String
    ): ResponseLeagues

    @GET(Constant.season)
    suspend fun getSeasonResponse(
        @Query(Constant.include) include: String,
        @Query(Constant.api_token) apiToken: String
    ): ResponseSeason

    @GET("${Constant.season}/{id}")
    suspend fun getSeasonByIdResponse(
        @Path("id") id: Int,
        @Query(Constant.include) include: String,
        @Query(Constant.api_token) apiToken: String
    ): ResponseSeasonForCard

    @GET("${Constant.teams}/{id}")
    suspend fun getTeamByIdResponse(
        @Path("id") id: Int,
        @Query(Constant.include) include: String,
        @Query(Constant.api_token) apiToken: String
    ): ResponseTeamDetails
}

object CricApi {
    val retrofitService: CricApiService by lazy { retrofit.create(CricApiService::class.java) }
}