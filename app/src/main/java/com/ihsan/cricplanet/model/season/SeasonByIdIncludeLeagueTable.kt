package com.ihsan.cricplanet.model.season

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "season")
data class SeasonByIdIncludeLeagueTable(
    @PrimaryKey
    val id: Int,
    val name: String?,
    val league_code: String?,
    val league_image_path: String?,
    val league_name: String?,
    val type: String?,
) {
    constructor() : this(
        0,
        null,
        null,
        null,
        null,
        null
    )
}