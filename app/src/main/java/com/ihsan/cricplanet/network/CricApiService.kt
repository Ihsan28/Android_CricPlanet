package com.ihsan.cricplanet.network

import com.ihsan.cricplanet.model.responseapi.*
import com.ihsan.cricplanet.utils.Constant
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://cricket.sportmonks.com/api/v2.0/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL)
        .build()

interface CricApiService {
    @GET(Constant.teams)
    suspend fun getTeamsResponse(@Query(Constant.api_token) api_token: String): ResponseTeam

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
        @Query(Constant.sort) sort: String,
        @Query(Constant.api_token) api_token: String
    ): ResponsePlayerCard
}

object CricApi {
    val retrofitService: CricApiService by lazy { retrofit.create(CricApiService::class.java) }
}