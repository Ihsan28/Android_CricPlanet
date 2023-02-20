package com.ihsan.cricplanet.roomdb.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ihsan.cricplanet.model.Team

@Dao
interface CricDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun storeTeams(team: List<Team>)

    @Query("SELECT * FROM team")
    fun readTeams(): LiveData<List<Team>>
}