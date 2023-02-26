package com.ihsan.cricplanet.roomdb.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ihsan.cricplanet.model.season.SeasonByIdIncludeLeagueTable

@Dao
interface CricDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun storeSeason(team: List<SeasonByIdIncludeLeagueTable>)

    @Query("SELECT * FROM season")
    fun readSeason(): LiveData<List<SeasonByIdIncludeLeagueTable>>
    @Query("SELECT * FROM season WHERE id = :id")
    fun readSeasonById(id:Int): LiveData<SeasonByIdIncludeLeagueTable>
}